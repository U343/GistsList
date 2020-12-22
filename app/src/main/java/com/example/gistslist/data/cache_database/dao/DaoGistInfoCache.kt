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
	abstract fun getSize(): Int

	@Query("DELETE FROM gistInfoModel")
	abstract fun deleteAll()

	@Query("SELECT * FROM gistInfoModel WHERE gist_id LIKE :gistId  LIMIT 1")
	abstract fun getByGistId(gistId: String): GistInfoModel?

	@Insert
	abstract fun insert(gistInfoElements: GistInfoModel)

	@Delete
	abstract fun delete(gistElement: GistInfoModel)
}