package com.example.gistslist.domain.application

/**
 * Фабрика кастомного класса application
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class CustomApplicationFactory {
	companion object {
		private var instance: CustomApplicationApi? = null

		fun getCustomApplication(): CustomApplicationApi {
			if (instance == null) {
				instance = CustomApplication()
			}
			return instance as CustomApplicationApi
		}
	}
}