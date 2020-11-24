package com.example.gistslist.data.gist_retrofit.query_interface

import com.example.gistslist.models.data.pojo.gist_info.GistInfoBean
import com.example.gistslist.models.data.pojo.gist_list.GistBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

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
	fun getGistsList(): Single<List<GistBean>>

	@GET("/gists/{gist_id}")
	fun getGistById(@Path("gist_id") gistId: String): Single<GistInfoBean>
}