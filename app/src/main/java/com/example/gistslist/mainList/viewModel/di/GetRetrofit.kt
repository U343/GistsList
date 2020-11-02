package com.example.gistslist.mainList.viewModel.di

import com.example.gistslist.gistsRetrofitModel.api.RetrofitApiInterface
import com.example.gistslist.gistsRetrofitModel.queryInterface.GistsRetrofitRequests
import com.example.gistslist.gistsRetrofitModel.launch.CreateRetrofitService

//TODO Здесь я пробую следавать солид и связываю Retrofit с ViewModel через интерфейс, но не уверен что это правильно
class GetRetrofit : RetrofitApiInterface {
	override fun getRetrofitService(): GistsRetrofitRequests {
		return CreateRetrofitService().getRetrofitService()
	}
}