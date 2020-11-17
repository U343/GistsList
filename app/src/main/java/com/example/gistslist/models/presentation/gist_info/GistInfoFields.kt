package com.example.gistslist.models.presentation.gist_info

/**
 * Класс со списком характеристик информации о гисте
 *
 * Для корректной работы должен совпадать с [gistInfoList] в [GistInfoViewModel]
 *
 * @author Dmitrii Bondarev on 17.11.2020
 */
class GistInfoFields {
	companion object {
		val fieldsNameList = arrayListOf(
			"Description",
			"Type",
			"Language",
			"Author login",
			"Link to Gist"
		)
	}
}