package com.example.shoppinglist.retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubGistApiService {

	@GET("gists/public")
	fun search(@Query("page") page: Int): Observable<ResultGists>
	//fun getGists(): Call<List<GistModel>>

	companion object Factory {
		fun create(): GithubGistApiService {
			val retrofit = Retrofit.Builder()
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.baseUrl("https://api.github.com/") //TODO вынести в константы
					.build()

			return retrofit.create(GithubGistApiService::class.java);
		}
	}
}