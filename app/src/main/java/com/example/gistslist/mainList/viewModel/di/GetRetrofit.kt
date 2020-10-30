package com.example.gistslist.mainList.viewModel.di

import com.example.gistslist.gistsRetrofitModel.api.RetrofitCommonObject
import com.example.gistslist.gistsRetrofitModel.queryInterface.RetrofitServices
import com.example.gistslist.gistsRetrofitModel.launch.CreateRetrofitGist
//TODO Здесь я пробую следавать солид и связываю Retrofit с ViewModel через интерфейс, но не уверен что это правильно
class GetRetrofit : RetrofitCommonObject {
	override fun getRetrofitService(): RetrofitServices {
		return CreateRetrofitGist().getRetrofitService()
	}
}