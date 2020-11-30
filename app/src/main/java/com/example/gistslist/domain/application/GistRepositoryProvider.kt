package com.example.gistslist.domain.application

import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.squareup.picasso.Picasso

/**
 * Кастомный класс application
 *
 * Хранит ссылку на репозиторий, который работает со списком гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
interface GistRepositoryProvider {
	/**
	 * Получение репозитория для списки гистов
	 *
	 * @return Возвращает репозитрий для работы с гистами
	 */
	fun getRepositoryGistList(): GistRepositoryApi

	/**
	 * Получение объекта Picasso для загрузки изображений
	 *
	 * @return Возвращает объект Picasso
	 */
	fun loadImage() : Picasso
}