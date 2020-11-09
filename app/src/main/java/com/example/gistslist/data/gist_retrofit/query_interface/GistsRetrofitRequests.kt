package com.example.gistslist.data.gist_retrofit.query_interface

import com.example.gistslist.models.data.pojo.GistBean
import retrofit2.Call
import retrofit2.http.GET

interface GistsRetrofitRequests {

	@GET("/gists/public")
	fun getGists(): Call<List<GistBean>>
}