package com.example.gistslist.domain.gist_repository

import com.example.gistslist.models.data.pojo.GistBean
import java.util.function.Consumer

/**
 * Репозиторий для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
interface IGistRepository {
	/**
	 * Получение списка POJO от Api гита
	 *
	 * Результаты запроса передаются через консумеры
	 *
	 * @param loadSuccess Консумер, который выполнится при удачной загрузке данных
	 * @param loadFail Консумер, который выполнится при ошибке загрузки данных
	 */
	fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>)
}