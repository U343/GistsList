package com.example.shoppinglist.mainList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainListViewModel : ViewModel() {
    //var numberList: ArrayList<String>? = ArrayList()
    val numberList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun generateNumberList(size: Int) {
        val   currentList = ArrayList<String>()

        for (i in 0 until size) {
            currentList.add(i.toString())
        }
        numberList.value = currentList
    }

//    fun clearNumberList() {
//        numberList?.clear()
//    }
}