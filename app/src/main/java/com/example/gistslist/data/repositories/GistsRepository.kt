package com.example.gistslist.data.repositories

import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist.GistBean
import io.reactivex.Single

/**
 * Реализация репозитория для работы со списком гистов
 *
 * Репозиторий, загружает гисты через апи гита и хранит список загруженныхгисто в [gistsList]
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistsRepository(private val gistApi: GistsApi) : GistRepositoryApi {

	override fun loadGistsList(): Single<List<GistBean>> {
		return gistApi.getGistsList()
	}

	override fun loadGistById(gistId: String): Single<GistBean> {
		return gistApi.getGistById(gistId)
	}
}