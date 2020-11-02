package com.example.gistslist.gistsRetrofitModel.queryInterface

import com.example.gistslist.gistsRetrofitModel.pojo.BasePojo
import retrofit2.Call
import retrofit2.http.GET

interface GistsRetrofitRequests {

    @GET("/gists/public")
    fun getGists(): Call<List<BasePojo>>
}