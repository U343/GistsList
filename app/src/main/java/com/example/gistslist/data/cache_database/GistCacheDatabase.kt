package com.example.gistslist.data.cache_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gistslist.data.cache_database.dao.DaoGistInfoCache
import com.example.gistslist.data.cache_database.dao.DaoGistListElementCache
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import com.example.gistslist.models.presentation.gist_model.GistListModel

@Database(entities = [GistListModel::class, GistInfoModel::class], version = 1)
abstract class GistCacheDatabase : RoomDatabase() {
	abstract fun gistListDao(): DaoGistListElementCache

	abstract fun gistInfoDao(): DaoGistInfoCache
}