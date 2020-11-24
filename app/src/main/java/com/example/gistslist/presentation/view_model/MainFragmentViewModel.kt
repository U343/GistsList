package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist_list.GistBean
import com.example.gistslist.models.presentation.gist_model.GistListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Вью модель для отображения списка гистов
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	private val dispose = CompositeDisposable()
	var isDataLoaded = false

	val gistsStringList: MutableLiveData<List<GistListModel>> by lazy {
		MutableLiveData<List<GistListModel>>()
	}

	val loadDataStatus: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
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
						loadDataStatus.value = false
						isDataLoaded = true
					},
					{ Log.d("onFailure", "fail MainFragmentViewModel") }
				)
		)
	}

	private fun generateGistModelList(pojoBeans: List<GistBean>): List<GistListModel> {
		return pojoBeans.map { bean ->
			GistListModel(
				bean.id,
				bean.files.keys.firstOrNull(),
				bean.description
			)
		}
	}
}