package com.example.gistslist.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.presentation.gist_model.GistModel

/**
 * Вью модель фргамента с информацией о гисте
 *
 * @param [repository] репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	var gistName: String? = null

	val gistInfoList: MutableLiveData<List<String?>> by lazy {
		MutableLiveData<List<String?>>()
	}

	fun generateGistInfoList(id: String?) {

	}

	/**
	 * Создание аррай листа на основе модели гиста
	 *
	 * Порядок элементов должен совпадать с [fieldsNameList] в [GistInfoFields]
	 *
	 * @param [model] модель гиста
	 */
	private fun setGistInfoList(model: GistModel) {
		gistInfoList.value = arrayListOf(
			model.gistDescription,
			model.gistType,
			model.language,
			model.userLogin,
			model.urlToGist
		)
	}
}