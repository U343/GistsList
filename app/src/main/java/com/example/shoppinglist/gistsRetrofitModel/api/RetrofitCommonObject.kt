package com.example.shoppinglist.gistsRetrofitModel.api

import com.example.shoppinglist.gistsRetrofitModel.queryInterface.RetrofitServices

interface RetrofitCommonObject {
	fun getRetrofitService(): RetrofitServices
}