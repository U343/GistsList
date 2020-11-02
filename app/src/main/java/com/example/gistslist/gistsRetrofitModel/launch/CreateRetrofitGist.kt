package com.example.gistslist.gistsRetrofitModel.launch

import com.example.gistslist.gistsRetrofitModel.api.RetrofitCommonObject
import com.example.gistslist.gistsRetrofitModel.queryInterface.RetrofitServices

private const val BASE_URL = "https://api.github.com"

class CreateRetrofitGist : RetrofitCommonObject {
		override fun getRetrofitService(): RetrofitServices {
			return RetrofitGist.getGist(BASE_URL)
				.create(RetrofitServices::class.java)
		}
}