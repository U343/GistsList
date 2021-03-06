package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist.GistBean
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Вью модель фргамента с информацией о гисте
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	private val dispose = CompositeDisposable()
	var isDataLoaded = false
	val gistInfoModel: MutableLiveData<GistInfoModel> = MutableLiveData<GistInfoModel>()
	val loadDataStatus: MutableLiveData<Boolean> =  MutableLiveData<Boolean>()

	override fun onCleared() {
		super.onCleared()

		dispose.clear()
	}

	fun getGistInfo(gistId: String?) {
		loadDataStatus.value = true

		viewModelScope.launch {
			val resultGistInfo = gistId?.let { repository.getGistInfoFromCacheById(it) }

			if (resultGistInfo == null) {
				Log.d("roomManage", "load from retrofit")
				loadGistInfoModel(gistId)
			} else {
				Log.d("roomManage", "load from cache")
				gistInfoModel.value = resultGistInfo
				isDataLoaded = true
				loadDataStatus.value = false
			}
		}
	}

	/**
	 * Загрузка информации о гисте
	 *
	 * После того как информация загружена, вызывается функция для загрузки аватара.
	 * Работает в отдельном потоке
	 *
	 * @param gistId индетификатор гиста, который необходимо загрузить
	 */
	fun loadGistInfoModel(gistId: String?) {
		viewModelScope.launch {
			getGistInfo(gistId)
		}
		gistId?.let {
			dispose.add(
				repository.getGistById(gistId)
					.subscribeOn(Schedulers.io())
					.map { createGistInfoModel(it) }
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(
						{ result ->

							viewModelScope.launch {
								repository.addGistInfoToCache(result)
							}

							gistInfoModel.value = result
							isDataLoaded = true
							loadDataStatus.value = false
						},
						{ Log.d("onFailure", "fail GistInfoViewModel") }
					)
			)
		}
	}

	private fun createGistInfoModel(bean: GistBean): GistInfoModel {
		return GistInfoModel(
			0,
			bean.id,
			bean.files.keys.firstOrNull(),
			bean.files[bean.files.keys.firstOrNull()]?.type,
			bean.files[bean.files.keys.firstOrNull()]?.language,
			bean.htmlUrl,
			bean.ownerInfoBean.login,
			bean.ownerInfoBean.avatarUrl,
			bean.description,
			bean.files[bean.files.keys.firstOrNull()]?.content
		)
	}
}