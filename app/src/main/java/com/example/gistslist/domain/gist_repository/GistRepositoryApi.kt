package com.example.gistslist.domain.gist_repository

import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.models.presentation.gist_model.GistModel
import java.util.function.Consumer

/**
 * Репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface GistRepositoryApi {
	/**
	 * Загрузка списка POJO из Api гита
	 *
	 * Результаты запроса передаются через консумеры, так как [loadGists] работает в отдельном
	 * потоке
	 *
	 * @param loadSuccess Консумер, который выполнится при удачной загрузке данных
	 * @param loadFail Консумер, который выполнится при ошибке загрузки данных
	 */
	fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>)

	/**
	 * Получение списка GistModel
	 *
	 * Вызывать только после [loadGists]
	 *
	 * @return аррей лист с гистами
	 */
	fun getGistsList() : ArrayList<GistModel>
}