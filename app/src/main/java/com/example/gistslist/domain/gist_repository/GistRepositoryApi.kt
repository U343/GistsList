package com.example.gistslist.domain.gist_repository

import com.example.gistslist.models.cache_database.GistListElementCache
import com.example.gistslist.models.data.pojo.gist.GistBean
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import com.example.gistslist.models.presentation.gist_model.GistListModel
import io.reactivex.Single

/**
 * Репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistRepositoryApi {
	/**
	 * Загрузка POJO объектов для основного списка гистов
	 *
	 * @return Возвращает Single объект с POJO для основного списка гистов
	 */
	fun loadGistsList(): Single<List<GistBean>>

	/**
	 * Загрузка POJO объекта с информацией о гисте по его id
	 *
	 * @param gistId id гиста, который нужно загрузить
	 * @return Возвращает Single объект с POJO с информацией о гисте
	 */
	fun getGistById(gistId: String): Single<GistBean>

	fun addGistListToCache(gistList: List<GistListModel>)

	fun getGistListFromCache(): List<GistListModel>

	fun addGistInfoToCache(gistInfo: GistInfoModel)

	fun getGistInfoFromCacheById(gistId: String): GistInfoModel?
}