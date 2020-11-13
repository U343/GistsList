package com.example.gistslist.domain.application

import android.app.Application
import com.example.gistslist.domain.gist_repository.GistRepositoryFactory
//TODO мне лично тут все нравится)) но правильно ли я все делаю с точки зрения архитектруы?
/**
 * Реализация кастомного класса application
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class CustomApplication : Application(), CustomApplicationApi {
	override val repositoryGistList = GistRepositoryFactory().getRepository()
}