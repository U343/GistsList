package com.example.gistslist.data.repositories

import com.example.gistslist.data.cache_database.GistCacheDatabase
import com.example.gistslist.data.cache_database.dao.DaoGistInfoCache
import com.example.gistslist.data.cache_database.dao.DaoGistListElementCache
import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.data.pojo.gist.GistBean
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import com.example.gistslist.models.presentation.gist_model.GistListModel
import io.reactivex.Single

/**
 * Реализация репозитория для работы со списком гистов
 *
 * Репозиторий, загружает гисты через апи гита и хранит список загруженныхгисто в [gistsList]
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistsRepository(private val gistApi: GistsApi, database: GistCacheDatabase) : GistRepositoryApi {

	var daoGistList: DaoGistListElementCache = database.gistListDao()
	var daoGistInfo: DaoGistInfoCache = database.gistInfoDao()

	override fun loadGistsList(): Single<List<GistBean>> {
		return gistApi.getGistsList()
	}

	override fun getGistById(gistId: String): Single<GistBean> {
		return gistApi.getGistById(gistId)
	}

	override suspend fun addGistListToCache(gistList: List<GistListModel>) {
		daoGistList.deleteAll()
		daoGistList.insertAll(gistList)
	}

	override suspend fun getGistListFromCache(): List<GistListModel> {
		return daoGistList.getAll()
	}

	/**
	 * Реализация функции по добавлению модели с информацией о гисте в кэш
	 *
	 * Проверяет, если в кэше хранится максимально допустимое количество моделей, то перед
	 * добавлением новой модели, кэш очищается
	 */
	override suspend fun addGistInfoToCache(gistInfo: GistInfoModel) {
		if (daoGistInfo.getSize() >= daoGistInfo.SIZE_LIMIT) {
			daoGistInfo.deleteAll()
		}
		daoGistInfo.insert(gistInfo)
	}

	override suspend fun getGistInfoFromCacheById(gistId: String): GistInfoModel? {
		return daoGistInfo.getByGistId(gistId)
	}
}