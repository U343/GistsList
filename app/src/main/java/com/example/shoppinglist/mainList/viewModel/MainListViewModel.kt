package com.example.shoppinglist.mainList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.shoppinglist.gistsRetrofitModel.pojo.BaseGist
import com.example.shoppinglist.mainList.viewModel.di.GetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListViewModel : ViewModel() {
    private lateinit var retrofitService: RetrofitServices

    val numberList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    val gistDataList: MutableLiveData<ArrayList<BaseGist>> by lazy {
        MutableLiveData<ArrayList<BaseGist>>()
    }

    fun generateNumberList(size: Int) {
        val   currentList = ArrayList<String>()

        for (i in 0 until size) {
            currentList.add(i.toString())
        }
        numberList.value = currentList
    }

    fun loadGists() {
        retrofitService = GetRetrofit().getRetrofitService()
        val call = retrofitService.getGists()
        call.enqueue(object : Callback<ArrayList<BaseGist>> {
            override fun onResponse(call: Call<ArrayList<BaseGist>>?, response: Response<ArrayList<BaseGist>>?) {
                gistDataList.value = response?.body()
                generateGistsList(gistDataList.value!!)
            }

            override fun onFailure(call: Call<ArrayList<BaseGist>>, t: Throwable) {
            }
        })
    }

    fun generateGistsList(gists: ArrayList<BaseGist>) {
        val   currentList = ArrayList<String>()

        for (i in gists) {
            currentList.add(i.description)
        }
        numberList.value = currentList
    }
}