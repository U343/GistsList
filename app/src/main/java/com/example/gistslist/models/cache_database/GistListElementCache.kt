package com.example.gistslist.models.cache_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GistListElementCache(
	@PrimaryKey val element_id: Int,
	@ColumnInfo(name = "gist_name") val gistName: String?,
	@ColumnInfo(name = "gist_description") val gistDescription: String?,
	@ColumnInfo(name = "gist_id") val gistId: String?
)
