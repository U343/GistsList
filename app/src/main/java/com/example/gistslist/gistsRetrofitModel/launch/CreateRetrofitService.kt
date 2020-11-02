package com.example.gistslist.gistsRetrofitModel.launch

import com.example.gistslist.gistsRetrofitModel.api.RetrofitApiInterface
import com.example.gistslist.gistsRetrofitModel.queryInterface.GistsRetrofitRequests

class CreateRetrofitService : RetrofitApiInterface {
	private val baseUrl = "https://api.github.com"

		override fun getRetrofitService(): GistsRetrofitRequests {
			return RetrofitObjectForLoadGists.getGist(baseUrl)
				.create(GistsRetrofitRequests::class.java)
		}
}