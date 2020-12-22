package com.example.gistslist.models.presentation.gist_model

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Класс с информацией для списка гистов
 *
 * Используется в MainGistListFragment для recycler view
 *
 * @property gistId идентификатор гиста
 * @property gistName имя гиста
 * @property gistDescription описание гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */

@Entity
data class GistListModel(
	@ColumnInfo(name = "gist_id") val gistId: String,
	@ColumnInfo(name = "gist_name") val gistName: String?,
	@ColumnInfo(name = "gist_description") val gistDescription: String?
)