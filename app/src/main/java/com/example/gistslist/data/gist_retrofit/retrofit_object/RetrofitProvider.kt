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
object RetrofitProvider {

	/**
	 * Аресс Api гита
	 */
	private const val BASE_URL = "https://api.github.com"

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
	 * @return Retrofit объект
	 */
	fun getRetrofitObject(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}