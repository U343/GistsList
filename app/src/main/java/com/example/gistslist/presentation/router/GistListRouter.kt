package com.example.gistslist.presentation.router

/**
 * Роутер для списка гистов
 *
 * @author Dmitrii Bondarev on 16.11.2020
 */
interface GistListRouter {
	/**
	 * Переход на основной экран со списком гистов
	 */
	fun goToMainGistListFragment()

	/**
	 * Переход на экран с подробной информацией о гисте
	 *
	 * @param gistId идентификатор выбранного гиста, по которому будет загружен гист
	 */
	fun goToGistInfoFragment(gistId: String)
}