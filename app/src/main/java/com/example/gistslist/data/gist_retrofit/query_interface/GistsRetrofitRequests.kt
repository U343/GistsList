package com.example.gistslist.data.gist_retrofit.query_interface

import com.example.gistslist.models.data.pojo.GistBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * Интерфейс HTTP запросов для retrofit обьекта гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistsRetrofitRequests {

	/**
	 * Получение Call объекта для дальнейшего GET запроса
	 *
	 * @return Call объект с со списком POJO
	 */
	@GET("/gists/public")
	fun getGists(): Call<List<GistBean>>
}