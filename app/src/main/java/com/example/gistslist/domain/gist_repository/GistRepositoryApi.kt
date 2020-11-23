package com.example.gistslist.domain.gist_repository

import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.models.presentation.gist_model.GistModel
import io.reactivex.Single
import java.util.function.Consumer

/**
 * Репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistRepositoryApi {
	/**
	 * Загрузка POJO объектов для основного списка гистов
	 *
	 * @return Возвращает Single объект с POJO для основного списка гистов
	 */
	fun loadGists() : Single<List<GistBean>>
}