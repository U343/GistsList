package com.example.shoppinglist.gistsRetrofitModel.api

import com.example.shoppinglist.gistsRetrofitModel.interfaceQuery.RetrofitServices

interface RetrofitCommonObject {
	fun getRetrofitService(): RetrofitServices
}