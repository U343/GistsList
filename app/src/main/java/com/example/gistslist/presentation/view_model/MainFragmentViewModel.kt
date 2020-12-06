package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist.GistBean
import com.example.gistslist.models.presentation.gist_model.GistListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Вью модель для отображения списка гистов
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	private val dispose = CompositeDisposable()
	private val subjectSearchGist: PublishSubject<List<GistListModel>> = PublishSubject.create()
	private lateinit var fullGistStringList: List<GistListModel>

	var isDataLoaded = false
	val gistsStringList: MutableLiveData<List<GistListModel>> = MutableLiveData<List<GistListModel>>()
	val loadDataStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

	init {
		dispose.add(subjectSearchGist
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				gistsStringList.value = it
		}, {
				Log.d("onFailure", "fail subjectSearchGist")
		}))
	}

	override fun onCleared() {
		super.onCleared()

		dispose.clear()
	}

	/**
	 * Формирование списка моделей гистов на основе ответа REST Api гита
	 *
	 * работает в отдельном потоке
	 */
	fun createGistsList() {
		loadDataStatus.value = true

		dispose.add(
			repository.loadGistsList()
				.subscribeOn(Schedulers.io())
				.map { generateGistModelList(it) }
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					{ result ->
						gistsStringList.value = result
						fullGistStringList = result.toList()
						loadDataStatus.value = false
						isDataLoaded = true
						Log.d("threads manage", "createGistsList " + Thread.currentThread())
					},
					{ Log.d("onFailure", "fail MainFragmentViewModel") }
				)
		)
	}

	//TODO понимаю что тут плохое решение, но пока как есть
	fun setSearchSymbols(s: CharSequence?) {
		if (s == null || s.length < 3) {
			subjectSearchGist.onNext(fullGistStringList.toList())
		} else {
			gistsStringList.value?.let { list ->
				subjectSearchGist.onNext(list.filter { model ->
					model.gistName!!.toLowerCase().startsWith(s.toString().toLowerCase())
				})
			}
		}
	}

	private fun generateGistModelList(pojoBeans: List<GistBean>): List<GistListModel> {
		Log.d("threads manage", "createGistsList into map" + Thread.currentThread())
		return pojoBeans.map { bean ->
			GistListModel(
				bean.id,
				bean.files.keys.firstOrNull(),
				bean.description
			)
		}
	}
}