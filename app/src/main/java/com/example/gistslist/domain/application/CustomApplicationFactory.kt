package com.example.gistslist.domain.application

/**
 * Фабрика кастомного класса application
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class CustomApplicationFactory {
	fun getCustomApplication() : CustomApplicationApi {
		return CustomApplication()
	}
}