package com.example.gistslist.mainList.viewModel.repository

import androidx.lifecycle.MutableLiveData
import com.example.gistslist.gistsRetrofitModel.pojo.BasePojo
import com.example.gistslist.mainList.viewModel.di.GetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistRepository {
	private val gistsRetrofitService = GetRetrofit().getRetrofitService()


	val pojoDataList: MutableLiveData<List<BasePojo>> by lazy {
		MutableLiveData<List<BasePojo>>()
	}

	fun loadGists() {
		val call = gistsRetrofitService.getGists()

		call.enqueue(object : Callback<List<BasePojo>> {
			override fun onResponse(call: Call<List<BasePojo>>?, response: Response<List<BasePojo>>?) {
				pojoDataList.value = response?.body()
			}

			override fun onFailure(call: Call<List<BasePojo>>, t: Throwable) {
			}
		})
	}
}