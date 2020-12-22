package com.example.gistslist.domain.gist_repository

import com.example.gistslist.data.cache_database.GistCacheDatabase
import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.data.repositories.GistsRepository

/**
 * Класс для получения экземпляра репозитория (репозитория для списка гистов)
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistRepositoryFactory(private val retrofit: GistsApi, private val database: GistCacheDatabase) {
	fun getRepository(): GistRepositoryApi {
		return GistsRepository(retrofit, database)
	}
}