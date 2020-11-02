package com.example.gistslist.gistsRetrofitModel.api

import com.example.gistslist.gistsRetrofitModel.queryInterface.GistsRetrofitRequests

interface RetrofitApiInterface {
	fun getRetrofitService(): GistsRetrofitRequests
}