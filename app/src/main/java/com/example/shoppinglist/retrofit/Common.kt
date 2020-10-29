package com.example.shoppinglist.retrofit

object Common {
    private const val BASE_URL = "https://api.github.com/gists/"

    val retrofitService: RetrofitServices?
        get() = RetrofitGist.getGist(BASE_URL)?.create(RetrofitServices::class.java)
}