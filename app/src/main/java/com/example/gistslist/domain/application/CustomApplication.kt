package com.example.gistslist.domain.application

import android.app.Application
import androidx.room.Room
import com.example.gistslist.data.cache_database.GistCacheDatabase
import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.data.gist_retrofit.retrofit_object.RetrofitProvider
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.domain.gist_repository.GistRepositoryFactory
import com.squareup.picasso.Picasso

/**
 * Реализация кастомного класса application
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class CustomApplication : Application(), GistRepositoryProvider {
	private lateinit var repositoryGistList: GistRepositoryApi
	private lateinit var databaseCache: GistCacheDatabase

	override fun onCreate() {
		super.onCreate()

		databaseCache = Room.databaseBuilder(
			applicationContext,
			GistCacheDatabase::class.java, "gist_cache_database"
		).build()

		val retrofitService = RetrofitProvider.getRetrofitObject().create(GistsApi::class.java)

		repositoryGistList = GistRepositoryFactory(retrofitService, databaseCache)
			.getRepository()
	}

	override fun getRepositoryGistList(): GistRepositoryApi {
		return repositoryGistList
	}

	override fun loadImage(): Picasso {
		return Picasso.get()
	}
}