package com.example.gistslist.presentation.view.di

import com.example.gistslist.domain.gists.GistsServiceApi
import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests
import com.example.gistslist.data.gist_retrofit.retrofit_object.CreateRetrofitServiceApi

//TODO Здесь я пробую следавать солид и связываю Retrofit с ViewModel через интерфейс, но не уверен что это правильно
class GetRetrofit : GistsServiceApi {
	override fun getRetrofitService(): GistsRetrofitRequests {
		return CreateRetrofitServiceApi().getRetrofitService()
	}
}