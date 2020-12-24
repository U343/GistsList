package com.example.gistslist.data.cache_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gistslist.models.presentation.gist_model.GistInfoModel

@Dao
abstract class DaoGistInfoCache {
	val SIZE_LIMIT = 5

	@Query("SELECT COUNT(url_to_gist) FROM gistInfoModel")
	abstract suspend fun getSize(): Int

	@Query("DELETE FROM gistInfoModel")
	abstract suspend fun deleteAll()

	@Query("SELECT * FROM gistInfoModel WHERE gist_id LIKE :gistId  LIMIT 1")
	abstract suspend fun getByGistId(gistId: String): GistInfoModel?

	@Insert
	abstract suspend fun insert(gistInfoElements: GistInfoModel)

	@Delete
	abstract suspend fun delete(gistElement: GistInfoModel)
}