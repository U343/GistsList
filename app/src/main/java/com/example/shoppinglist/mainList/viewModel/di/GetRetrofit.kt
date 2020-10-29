package com.example.shoppinglist.mainList.viewModel.di

import com.example.shoppinglist.gistsRetrofitModel.api.RetrofitCommonObject
import com.example.shoppinglist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.shoppinglist.gistsRetrofitModel.launch.CreateRetrofitGist

class GetRetrofit : RetrofitCommonObject {
	override fun getRetrofitService(): RetrofitServices {
		return CreateRetrofitGist().getRetrofitService()
	}
}