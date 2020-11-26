package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist_info.GistInfoBean
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Вью модель фргамента с информацией о гисте
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	private val dispose = CompositeDisposable()
	var isDataLoaded = false;

	val gistInfoModel: MutableLiveData<GistInfoModel> by lazy {
		MutableLiveData<GistInfoModel>()
	}

	val userAvatar: MutableLiveData<RequestCreator?> by lazy {
		MutableLiveData<RequestCreator?>()
	}

	val loadDataStatus: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}

	override fun onCleared() {
		super.onCleared()

		dispose.clear()
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
		loadDataStatus.value = true

		gistId?.let {
			dispose.add(
				repository.loadGistById(gistId)
					.subscribeOn(Schedulers.io())
					.map { createGistInfoModel(it) }
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(
						{ result ->
							gistInfoModel.value = result
							result.avatarUrl?.let { it1 -> loadAuthorAvatar(it1) }
							isDataLoaded = true
							loadDataStatus.value = false
						},
						{ Log.d("onFailure", "fail GistInfoViewModel") }
					)
			)
		}
	}

	private fun loadAuthorAvatar(urlToAvatar: String) {
		userAvatar.value = Picasso.get().load(urlToAvatar)
	}

	private fun createGistInfoModel(bean: GistInfoBean): GistInfoModel {
		return GistInfoModel(
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