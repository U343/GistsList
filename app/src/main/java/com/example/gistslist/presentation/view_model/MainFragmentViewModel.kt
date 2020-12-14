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

	private val subjectSearchGist: PublishSubject<String> = PublishSubject.create()
	private val subjectLoadGist: PublishSubject<String> = PublishSubject.create()
	private lateinit var fullGistStringList: List<GistListModel>

	var isDataLoaded = false
	val gistsStringList: MutableLiveData<List<GistListModel>> = MutableLiveData<List<GistListModel>>()
	val loadDataStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

	init {
		val merged = Observable.merge(subjectSearchGist, subjectLoadGist)
			.switchMapSingle {
				createObservable(it)
			}
			.subscribeOn(Schedulers.io())
			.map { generateGistModelList(it) }
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe ({
				gistsStringList.value = it
				fullGistStringList = it
				loadDataStatus.value = false
				isDataLoaded = true
			},
			{ Log.d("threadsManage", "Error init merge" + Thread.currentThread()) })

		dispose.add(merged)
	}

	private fun createObservable(s: String): Single<List<GistBean>> {
		return if (s == "refresh") {
			repository
				.loadGistsList()
//				.doOnSuccess {
//					fullGistStringList = it
//					loadDataStatus.value = false
//					isDataLoaded = true
//				}
		} else {
			Single.create {
				createSearchList(s)
			}
		}
	}

	private fun createSearchList(template: String?): List<GistListModel> {
		val searchedGistList = ArrayList<GistListModel>()

		if (template == null || template == "") {
			return fullGistStringList
		}

		for (i in fullGistStringList) {
			if (i.gistName?.toLowerCase(Locale.getDefault())
					?.startsWith(template.toLowerCase(Locale.getDefault())) == true
			) {
				searchedGistList.add(i)
			}
		}
		return searchedGistList
	}

	override fun onCleared() {
		super.onCleared()

		dispose.clear()
	}

	fun loadGistsList() {
		loadDataStatus.value = true
		subjectLoadGist.onNext("refresh")
	}

	fun searchedGist(s: String) {
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