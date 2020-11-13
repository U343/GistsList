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

	val gistObject: MutableLiveData<GistModel> by lazy {
		MutableLiveData<GistModel>()
	}

	fun generateGistModel(id: Int) {
		gistObject.value = repository.getGistById(id)
	}
}