package com.example.gistslist.data.cache_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gistslist.models.presentation.gist_model.GistListModel

@Dao
interface DaoGistListElementCache {

	@Query("SELECT * FROM gistListModel")
	suspend fun getAll(): List<GistListModel>

	@Query("DELETE FROM gistListModel")
	suspend fun deleteAll()

	@Insert
	 suspend fun insertAll(gistElements: List<GistListModel>)
}