package com.example.gistslist.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.domain.gist_repository.IGistRepository

/**
 * Фабрика вью модели списка гистов
 *
 * Позволяет передать вью модели [IGistRepository] параметр
 *
 * @param repository репозиторий списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentModelViewFactory(private val repository: IGistRepository) :
	ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return MainFragmentViewModel(repository) as T
	}
}