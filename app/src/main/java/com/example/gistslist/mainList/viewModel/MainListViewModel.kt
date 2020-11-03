package com.example.gistslist.mainList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.gistslist.gistsRetrofitModel.pojo.BasePojo
import com.example.gistslist.mainList.viewModel.repository.GistRepository
import kotlin.Exception

class MainListViewModel : ViewModel() {
    private val repository = GistRepository()

    val gistsStringList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    private val gistsStringListObserver = Observer<List<BasePojo>> { pojoDataList ->
        generateGistsList(pojoDataList)
    }

    init {
        repository.pojoDataList.observeForever(gistsStringListObserver)
    }

    fun getGistsList() {
        repository.loadGists()
    }

    private fun generateGistsList(pojos: List<BasePojo>?) {
        val   currentList = ArrayList<String>()

        if (pojos != null) {
            for (i in pojos) {
                try {
                    currentList.add(i.files.keys.elementAt(0))
                } catch (e: Exception) {
                    continue
                }
            }
            gistsStringList.value = currentList
        }
    }
}