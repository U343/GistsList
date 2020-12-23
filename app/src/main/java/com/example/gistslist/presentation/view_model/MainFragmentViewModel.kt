package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist.GistBean
import com.example.gistslist.models.presentation.gist_model.GistListModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*
import kotlin.collections.ArrayList

/**
 * Вью модель для отображения списка гистов
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	private val dispose = CompositeDisposable()

	private val subjectSearchGist: Subject<String> = BehaviorSubject.createDefault("")
	private val subjectLoadGist: Subject<List<GistListModel>> = PublishSubject.create()

	var isDataLoaded = false
	val gistsStringList: MutableLiveData<List<GistListModel>> =
		MutableLiveData<List<GistListModel>>()
	val loadDataStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

	init {
		val searchString = subjectSearchGist

		val merged = Observable.combineLatest(searchString, subjectLoadGist) { template, rep ->
			createGistsList(template, rep)
		}.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe( { gistsStringList.value = it },
			 { Log.d("threadsManage", "Error combineLatest " + Thread.currentThread()) })


		dispose.add(merged)
	}

	private fun createGistsList(
		template: String?,
		gistList: List<GistListModel>
	): List<GistListModel> {
		Log.d("threadsManage", "createGistsList " + Thread.currentThread())
		val searchedArray = ArrayList<GistListModel>()

		isDataLoaded = true
		loadDataStatus.value = false

		return if (template == null || template == "") {
			gistList
		} else {
			for (i in gistList) {
				if (i.gistName?.toLowerCase(Locale.getDefault())
						?.startsWith(template.toLowerCase(Locale.getDefault())) == true
				) {
					searchedArray.add(i)
				}
			}
			searchedArray
		}
	}

	override fun onCleared() {
		super.onCleared()

		dispose.clear()
	}

	fun searchedGist(s: String) {
		Log.d("threadsManage", "searchedGist " + Thread.currentThread())
		subjectSearchGist.onNext(s)
	}

	fun loadGistsList() {
		loadDataStatus.value = true

		Log.d("threadsManage", "into loadGistsList " + Thread.currentThread())
		dispose.add(repository.loadGistsList()
			.subscribeOn(Schedulers.io())
			.map { transformGistBeansToGistModels(it) }
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				subjectLoadGist.onNext(it)
			},
				{ Log.d("threadsManage", "Error createGistList " + Thread.currentThread()) })
		)
	}

	private fun transformGistBeansToGistModels(pojoBeans: List<GistBean>): List<GistListModel> {
		Log.d("threadsManage", "transformGistBeansToGistModels " + Thread.currentThread())
		return pojoBeans.map { bean ->
			GistListModel(
				0,
				bean.id,
				bean.files.keys.firstOrNull(),
				bean.description
			)
		}
	}
}