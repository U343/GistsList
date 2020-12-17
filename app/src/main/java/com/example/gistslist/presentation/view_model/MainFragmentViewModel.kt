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

	private val subjectSearchGist: Subject<String> = PublishSubject.create()
	private val subjectLoadGist: Subject<Int> = PublishSubject.create()

	var isDataLoaded = false
	val gistsStringList: MutableLiveData<List<GistListModel>> = MutableLiveData<List<GistListModel>>()
	val loadDataStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

	init {
		val searchQuery = subjectSearchGist
			.switchMap { str->
				Log.d("threadsManage", "gists map $str")
				Observable.create<String> { str } }

		val gists = subjectLoadGist
			.switchMapSingle {
				repository
					.loadGistsList()
					.map {
						Log.d("threadsManage", "gists map $it")
						generateGistModelList(it)
					}
			}

		val merged = Observable.combineLatest(searchQuery, gists) { template, list ->
			createGistsList(template, list)
		}.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ result ->
				gistsStringList.value = result
			}, {
				Log.d("threadsManage", "Error combineLatest " + Thread.currentThread())
			})

		dispose.add(merged)
	}

	private fun createGistsList(template: String?, gistList: List<GistListModel>): List<GistListModel> {
		Log.d("threadsManage", "createGistsList $gistList")
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

	fun loadGistsList() {
		Log.d("threadsManage", "loadGistsList " + Thread.currentThread())
		loadDataStatus.value = true
		subjectLoadGist.onNext(1)
	}

	fun searchedGist(s: String) {
		Log.d("threadsManage", "searchedGist " + Thread.currentThread())
		subjectSearchGist.onNext(s)
	}

	private fun generateGistModelList(pojoBeans: List<GistBean>): List<GistListModel> {
		Log.d("threadsManage", "createGistsList into map" + Thread.currentThread())
		return pojoBeans.map { bean ->
			GistListModel(
				bean.id,
				bean.files.keys.firstOrNull(),
				bean.description
			)
		}
	}
}