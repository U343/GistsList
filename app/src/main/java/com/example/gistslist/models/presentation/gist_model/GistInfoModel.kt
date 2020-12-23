package com.example.gistslist.models.presentation.gist_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс с информацией о гисте
 *
 * Используется в GistInfoFragment
 *
 * @property gistName имя гиста
 * @property gistType тип гиста
 * @property gistLanguage язык программирования, который содержится в гисте
 * @property urlToGist ссылка на гист в github
 * @property authorLogin логин github автора гиста
 * @property avatarUrl ссылка на аватар автора гиста
 * @property gistDescription описание гиста
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */

@Entity
data class GistInfoModel(
	@PrimaryKey(autoGenerate = true) val infoId: Int,
	@ColumnInfo(name = "gist_id") val gistId: String?,
	@ColumnInfo(name = "gist_name") val gistName: String?,
	@ColumnInfo(name = "gist_type") val gistType: String?,
	@ColumnInfo(name = "gist_language") val gistLanguage: String?,
	@ColumnInfo(name = "url_to_gist") val urlToGist: String?,
	@ColumnInfo(name = "author_login") val authorLogin: String?,
	@ColumnInfo(name = "avatar_url") val avatarUrl: String?,
	@ColumnInfo(name = "gist_description") val gistDescription: String?,
	@ColumnInfo(name = "gist_content") val gistContent: String?
)