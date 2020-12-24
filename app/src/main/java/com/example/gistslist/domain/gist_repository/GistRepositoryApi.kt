package com.example.gistslist.domain.gist_repository

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

	/**
	 * Добавление списка моделей гистов в базу данных с кэшом
	 *
	 * @param gistList список гистов
	 */
	suspend fun addGistListToCache(gistList: List<GistListModel>)

	/**
	 * Получение списка моделей гистов из кэша
	 *
	 * @return список гистов или null если кэш пустой
	 */
	suspend fun getGistListFromCache(): List<GistListModel>?

	/**
	 * Добавление модели с информацией о гисте в кэш
	 *
	 * @param gistInfo модель с информацией о гисте
	 */
	suspend fun addGistInfoToCache(gistInfo: GistInfoModel)

	/**
	 * Получение модели с информацией из кэша по id
	 *
	 * @param gistId id гиста
	 * @return модель с информацией о гисте или null если гист не найден
	 */
	suspend fun getGistInfoFromCacheById(gistId: String): GistInfoModel?
}