package com.example.gistslist.gistsRetrofitModel.api

import com.example.gistslist.gistsRetrofitModel.queryInterface.RetrofitServices

interface RetrofitCommonObject {
	fun getRetrofitService(): RetrofitServices
}