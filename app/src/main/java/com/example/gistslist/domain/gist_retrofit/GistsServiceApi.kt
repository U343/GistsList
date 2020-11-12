package com.example.gistslist.domain.gist_retrofit

import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests

/**
 * Api Retrofit сервиса
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistsServiceApi {
	/**
	 * @return возвращает объект Retrofit сервиса
	 */
	fun getRetrofitService(): GistsRetrofitRequests
}