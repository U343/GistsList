package com.example.shoppinglist.retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("gists/public")
    fun getGists(): Call<List<GistPOJO>>
}