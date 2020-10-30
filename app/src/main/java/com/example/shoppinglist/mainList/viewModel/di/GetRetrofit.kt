package com.example.shoppinglist.mainList.viewModel.di

import com.example.shoppinglist.gistsRetrofitModel.api.RetrofitCommonObject
import com.example.shoppinglist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.shoppinglist.gistsRetrofitModel.launch.CreateRetrofitGist
//TODO Здесь я пробую следавать солид и связываю Retrofit с ViewModel через интерфейс, но не уверен что это правильно
class GetRetrofit : RetrofitCommonObject {
	override fun getRetrofitService(): RetrofitServices {
		return CreateRetrofitGist().getRetrofitService()
	}
}