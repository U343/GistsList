package com.example.shoppinglist.gistsRetrofitModel.launch

import com.example.shoppinglist.gistsRetrofitModel.api.RetrofitCommonObject
import com.example.shoppinglist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.shoppinglist.gistsRetrofitModel.url.BASE_URL

class CreateRetrofitGist : RetrofitCommonObject {
		override fun getRetrofitService(): RetrofitServices {
			return RetrofitGist.getGist(BASE_URL).create(RetrofitServices::class.java)
		}
}