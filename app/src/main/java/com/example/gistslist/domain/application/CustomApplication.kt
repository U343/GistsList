package com.example.gistslist.domain.application

import android.app.Application
import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.data.gist_retrofit.retrofit_object.RetrofitProvider
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.domain.gist_repository.GistRepositoryFactory

/**
 * Реализация кастомного класса application
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class CustomApplication : Application(), GistRepositoryProvider {
	private lateinit var repositoryGistList: GistRepositoryApi

	override fun onCreate() {
		super.onCreate()

		val retrofitService = RetrofitProvider.getRetrofitObject().create(GistsApi::class.java)
		repositoryGistList = GistRepositoryFactory(retrofitService)
			.getRepository()
	}

	override fun getRepositoryGistList(): GistRepositoryApi {
		return repositoryGistList
	}
}