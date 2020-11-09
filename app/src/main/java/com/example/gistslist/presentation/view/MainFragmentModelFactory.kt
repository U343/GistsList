package com.example.gistslist.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.domain.gist_repository.IGistRepository

class MainFragmentModelFactory(private val repository: IGistRepository) :
	ViewModelProvider.Factory {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return MainFragmentModel(repository) as T
	}
}