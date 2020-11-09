package com.example.gistslist.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.retrofit_gist.GetRetrofitService
import com.example.gistslist.domain.gist_repository.IGistRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistsRepository : IGistRepository {
	private val gistsRetrofitService = GetRetrofitService().getRetrofitService()


	override val pojoDataList: MutableLiveData<List<GistBean>> by lazy {
		MutableLiveData<List<GistBean>>()
	}

	override fun loadGists() {
		val call = gistsRetrofitService.getGists()

		call.enqueue(object : Callback<List<GistBean>> {
			override fun onResponse(
				call: Call<List<GistBean>>?,
				response: Response<List<GistBean>>?
			) {
				pojoDataList.value = response?.body()
			}

			override fun onFailure(call: Call<List<GistBean>>, t: Throwable) {
			}
		})
	}
}