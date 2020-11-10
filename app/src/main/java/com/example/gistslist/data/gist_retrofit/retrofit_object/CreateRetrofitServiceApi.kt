package com.example.gistslist.data.gist_retrofit.retrofit_object

import com.example.gistslist.domain.retrofit_gist.GistsServiceApi
import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests

/**
 * Класс для создания Retrofit сервиса, связывает Retrofit обьект и интерфейс HTTP запросов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class CreateRetrofitServiceApi : GistsServiceApi {

	/**
	 * Аресс Api гита
	 */
	private val baseUrl = "https://api.github.com"

	override fun getRetrofitService(): GistsRetrofitRequests {
		return RetrofitObjectForLoadGists.getGist(baseUrl)
			.create(GistsRetrofitRequests::class.java)
	}
}