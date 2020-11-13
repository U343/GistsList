package com.example.gistslist.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.domain.gist_repository.GistRepositoryApi

/**
 * Фабрика вью модели с информацией о гисте
 *
 * Позволяет передать вью модели [GistRepositoryApi] параметр
 *
 * @param repository репозиторий списка гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoViewModelFactory(private val repository: GistRepositoryApi) :
	ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return GistInfoViewModel(repository) as T
	}
}