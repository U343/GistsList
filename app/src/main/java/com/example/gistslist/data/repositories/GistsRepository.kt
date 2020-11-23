package com.example.gistslist.data.repositories

import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import io.reactivex.Single

/**
 * Реализация репозитория для работы со списком гистов
 *
 * Репозиторий, загружает гисты через апи гита и хранит список загруженныхгисто в [gistsList]
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistsRepository(private val retrofit: GistsApi) : GistRepositoryApi {

	override fun loadGists(): Single<List<GistBean>> {
		return retrofit.getGists()
	}
}