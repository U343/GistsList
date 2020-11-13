package com.example.gistslist.domain.gist_repository

import com.example.gistslist.data.repositories.GistsRepository

/**
 * Класс для получения экземпляра репозитория (репозитория для списка гистов)
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistRepositoryFactory {
	fun getRepository(): GistRepositoryApi {
		return GistsRepository()
	}
}