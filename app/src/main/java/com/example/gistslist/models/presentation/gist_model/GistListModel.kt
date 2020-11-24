package com.example.gistslist.models.presentation.gist_model

/**
 * Класс с информацией о гисте
 *
 * Используется в MainGistListFragment для recycler view
 *
 * @property userLogin логин автора гиста
 * @property gistDescription описание гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
data class GistListModel(
	val gistId: String,
	val gistName: String?,
	val gistDescription: String?
)