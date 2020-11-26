package com.example.gistslist.data.gist_retrofit.query_interface

import com.example.gistslist.models.data.pojo.gist.GistBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Интерфейс HTTP запросов к Api гита для retrofit
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistsApi {

	/**
	 * Получение Single объекта с запросом списка гистов
	 *
	 * @return Single объект с со списком POJO для списка гистов
	 */
	@GET("/gists/public")
	fun getGistsList(): Single<List<GistBean>>

	/**
	 * Получение Single объекта с запросом гиста по id
	 *
	 * @param gistId id гиста
	 * @return Single объект с POJO для информации о гисте
	 */
	@GET("/gists/{gist_id}")
	fun getGistById(@Path("gist_id") gistId: String): Single<GistBean>
}