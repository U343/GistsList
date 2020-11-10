package com.example.gistslist.presentation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.gistslist.models.presentation.gist_model.GistModel
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_repository.IGistRepository

class MainFragmentModel(private val repository: IGistRepository) : ViewModel() {
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

	private fun generateGistsList(pojoList: List<GistBean>?) {
		val currentList = ArrayList<GistModel>()

		if (pojoList != null) {
			for (i in pojoList) {
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