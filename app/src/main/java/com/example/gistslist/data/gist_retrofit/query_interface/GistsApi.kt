package com.example.gistslist.data.gist_retrofit.query_interface

import com.example.gistslist.models.data.pojo.GistBean
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

/**
 * Интерфейс HTTP запросов для retrofit обьекта гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistsApi {

	/**
	 * Получение Call объекта для дальнейшего GET запроса
	 *
	 * @return Call объект с со списком POJO
	 */
	@GET("/gists/public")
	fun getGists(): Single<List<GistBean>>
}