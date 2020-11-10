package com.example.gistslist.data.gist_retrofit.retrofit_object

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Класс для создания Retrofit обьекта
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class RetrofitObjectForLoadGists {

	companion object {
		private val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
			.setLevel(HttpLoggingInterceptor.Level.BODY)

		/**
		 * Объект для создания логов
		 */
		private val client: OkHttpClient = OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.build()

		/**
		 * Получить объект Retrofit
		 *
		 * @param baseUrl Адресс Api
		 * @return Retrofit объект
		 */
		fun getGist(baseUrl: String): Retrofit {
			return Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
		}
	}
}