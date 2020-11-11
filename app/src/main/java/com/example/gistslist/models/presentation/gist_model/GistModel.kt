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
data class GistModel(
	val userLogin: String?,
	val gistDescription: String?
)