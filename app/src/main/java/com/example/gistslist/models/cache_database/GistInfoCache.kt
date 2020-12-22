package com.example.gistslist.models.cache_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GistInfoCache(
	@PrimaryKey val element_id: Int,
	@ColumnInfo(name = "gist_name") val gistName: String?,
	@ColumnInfo(name = "gist_type") val gistType: String?,
	@ColumnInfo(name = "gist_language") val gistLanguage: String?,
	@ColumnInfo(name = "url_to_gist") val urlToGist: String?,
	@ColumnInfo(name = "author_login") val authorLogin: String?,
	@ColumnInfo(name = "avatar_url") val avatarUrl: String?,
	@ColumnInfo(name = "gist_description") val gistDescription: String?,
	@ColumnInfo(name = "gist_content") val gistContent: String?,
)
