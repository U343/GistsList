package com.example.gistslist.data.gist_retrofit.retrofit_object

import com.example.gistslist.domain.retrofit_gist.GistsServiceApi
import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests

class CreateRetrofitServiceApi : GistsServiceApi {
	private val baseUrl = "https://api.github.com"

	override fun getRetrofitService(): GistsRetrofitRequests {
		return RetrofitObjectForLoadGists.getGist(baseUrl)
			.create(GistsRetrofitRequests::class.java)
	}
}