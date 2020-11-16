package com.example.gistslist.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.presentation.gist_model.GistModel

/**
 * Вью модель фргамента с информацией о гисте
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoViewModel(private val repository: GistRepositoryApi) : ViewModel() {

	val gistInfoList: MutableLiveData<ArrayList<String?>> by lazy {
		MutableLiveData<ArrayList<String?>>()
	}

	fun generateGistInfoList(id: Int) {
		val model =  repository.getGistById(id)

		if (model != null) {
			setGistInfoList(model)
		}
	}

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