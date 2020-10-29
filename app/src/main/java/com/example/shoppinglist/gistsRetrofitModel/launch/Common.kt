package com.example.shoppinglist.gistsRetrofitModel.launch

import com.example.shoppinglist.gistsRetrofitModel.interfaceQuery.RetrofitServices

class Common {
    companion object {
        private const val BASE_URL = "https://api.github.com"

        val retrofitService: RetrofitServices?
            get() = RetrofitGist.getGist(BASE_URL).create(RetrofitServices::class.java)
    }
}