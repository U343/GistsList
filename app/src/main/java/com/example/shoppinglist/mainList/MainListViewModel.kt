package com.example.shoppinglist.mainList

import androidx.lifecycle.ViewModel

class MainListViewModel : ViewModel() {
    var numberList: ArrayList<String>? = null

    override fun onCleared() {
        super.onCleared()
    }

    fun	generateNumberList(size: Int) {
        numberList = ArrayList()

        for (i in 0 until size) {
            numberList?.add(i.toString())
        }
    }

    fun clearNumberList() {
        numberList?.clear()
    }
}