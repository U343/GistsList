package com.example.shoppinglist.gistsRetrofitModel.interfaceQuery

import com.example.shoppinglist.gistsRetrofitModel.pojo.BaseGist
import retrofit2.Call
import retrofit2.http.GET



interface RetrofitServices {

    @GET("/gists/public")
    fun getGists(): Call<ArrayList<BaseGist>>
}