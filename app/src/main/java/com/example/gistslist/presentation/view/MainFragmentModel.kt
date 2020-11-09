package com.example.gistslist.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.gistslist.models.data.gist.GistModel
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.data.repositories.GistsRepository
import kotlin.Exception

class MainFragmentModel : ViewModel() {
	private val repository = GistsRepository()

	val gistsStringList: MutableLiveData<ArrayList<GistModel>> by lazy {
		MutableLiveData<ArrayList<GistModel>>()
	}

	val loadDataStatus: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}

	private val gistsStringListObserver = Observer<List<GistBean>> { pojoDataList ->
		generateGistsList(pojoDataList)
	}

	init {
		repository.pojoDataList.observeForever(gistsStringListObserver)
	}

	fun getGistsList() {
		repository.loadGists()
		loadDataStatus.value = true
	}

	private fun generateGistsList(pojos: List<GistBean>?) {
		val currentList = ArrayList<GistModel>()

		if (pojos != null) {
			for (i in pojos) {
				try {
					currentList.add(
						GistModel(
							i.files.keys.elementAt(0),
							i.description
						)
					)
				} catch (e: Exception) {
					continue
				}
			}
			gistsStringList.value = currentList
			loadDataStatus.value = false
		}
	}
}