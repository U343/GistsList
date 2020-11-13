package com.example.gistslist.domain.application

import com.example.gistslist.domain.gist_repository.GistRepositoryApi

/**
 * Кастомный класс application
 *
 * Хранит ссылку на репозиторий, который работает со списком гистов
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
interface CustomApplicationApi {
	val repositoryGistList: GistRepositoryApi
}