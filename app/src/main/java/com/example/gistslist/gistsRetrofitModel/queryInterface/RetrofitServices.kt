package com.example.gistslist.gistsRetrofitModel.queryInterface

import com.example.gistslist.gistsRetrofitModel.pojo.BaseGist
import com.example.gistslist.gistsRetrofitModel.url.LOCAL_URL_GISTS_PUBLIC
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {

    @GET(LOCAL_URL_GISTS_PUBLIC)
    fun getGists(): Call<List<BaseGist>>
}