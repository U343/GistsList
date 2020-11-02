package com.example.gistslist.gistsRetrofitModel.queryInterface

import com.example.gistslist.gistsRetrofitModel.pojo.BaseGist
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {

    @GET("/gists/public")
    fun getGists(): Call<List<BaseGist>>
}