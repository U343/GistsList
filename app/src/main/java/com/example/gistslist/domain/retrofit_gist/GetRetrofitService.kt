package com.example.gistslist.domain.retrofit_gist

import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests
import com.example.gistslist.data.gist_retrofit.retrofit_object.CreateRetrofitServiceApi

/**
 * Класс для получения экземпляра Retrofit сервиса
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GetRetrofitService : GistsServiceApi {
	override fun getRetrofitService(): GistsRetrofitRequests {
		return CreateRetrofitServiceApi().getRetrofitService()
	}
}