package com.example.gistslist.mainList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.MainActivity
import com.example.gistslist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.gistslist.gistsRetrofitModel.pojo.BaseGist
import com.example.gistslist.mainList.viewModel.di.GetRetrofit
import com.example.gistslist.showLoadingErrorToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListViewModel : ViewModel() {
    private lateinit var retrofitService: RetrofitServices
    val isLoadGistSuccess: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val numberList: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    val gistDataList: MutableLiveData<List<BaseGist>> by lazy {
        MutableLiveData<List<BaseGist>>()
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
        retrofitService = GetRetrofit().getRetrofitService()
        val call = retrofitService.getGists()
        call.enqueue(object : Callback<List<BaseGist>> {
            override fun onResponse(call: Call<List<BaseGist>>?, response: Response<List<BaseGist>>?) {
                isLoadGistSuccess.value = true
                gistDataList.value = response?.body()
                if (gistDataList.value != null) {
                    generateGistsList(gistDataList.value!!) //TODO почему тут нужны !! я же делаю проверку на null
                }
            }

            override fun onFailure(call: Call<List<BaseGist>>, t: Throwable) {
                isLoadGistSuccess.value = false
            }
        })
    }

    fun generateGistsList(gists: List<BaseGist>) {
        val   currentList = ArrayList<String>()

        for (i in gists) {
            currentList.add(i.owner.login)
        }
        numberList.value = currentList
    }
}