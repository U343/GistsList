package com.example.gistslist.mainList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.gistsRetrofitModel.queryInterface.GistsRetrofitRequests
import com.example.gistslist.gistsRetrofitModel.pojo.BasePojo
import com.example.gistslist.mainList.viewModel.di.GetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListViewModel : ViewModel() {
    private lateinit var gistsRetrofitService: GistsRetrofitRequests
    val isLoadGistSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val numberList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    val pojoDataList: MutableLiveData<List<BasePojo>> by lazy {
        MutableLiveData<List<BasePojo>>()
    }

    fun generateNumberList(size: Int) {
        val   currentList = ArrayList<String>()

        for (i in 0 until size) {
            currentList.add(i.toString())
        }
        numberList.value = currentList
    }
/*TODO тут сомнения по правильности того что делаю. Нормально что эта функция здесь, может ее лучше по другому связать с
viewModel, кажется что здесь нарушаются правила солид, тип слишком жестко связан retrofit и модель
 */
    fun loadGists() {
        gistsRetrofitService = GetRetrofit().getRetrofitService()
        val call = gistsRetrofitService.getGists()
        call.enqueue(object : Callback<List<BasePojo>> {
            override fun onResponse(call: Call<List<BasePojo>>?, response: Response<List<BasePojo>>?) {
                isLoadGistSuccess.value = true
                pojoDataList.value = response?.body()
                if (pojoDataList.value != null) {
                    generateGistsList(pojoDataList.value!!) //TODO почему тут нужны !! я же делаю проверку на null
                }
            }

            override fun onFailure(call: Call<List<BasePojo>>, t: Throwable) {
                isLoadGistSuccess.value = false
            }
        })
    }

    fun generateGistsList(pojos: List<BasePojo>) {
        val   currentList = ArrayList<String>()

        for (i in pojos) {
            currentList.add(i.files.values.toTypedArray()[0].filename)
        }
        numberList.value = currentList
    }
}