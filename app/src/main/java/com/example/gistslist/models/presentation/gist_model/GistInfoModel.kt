package com.example.gistslist.models.presentation.gist_model

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
data class GistInfoModel(
	val gistName: String?,
	val gistType: String?,
	val gistLanguage: String?,
	val urlToGist: String?,
	val authorLogin: String?,
	val avatarUrl: String?,
	val gistDescription: String?,
	val gistContent: String?
)