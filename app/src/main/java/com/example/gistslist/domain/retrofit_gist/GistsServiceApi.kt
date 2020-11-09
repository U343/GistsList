package com.example.gistslist.domain.retrofit_gist

import com.example.gistslist.data.gist_retrofit.query_interface.GistsRetrofitRequests

interface GistsServiceApi {
	fun getRetrofitService(): GistsRetrofitRequests
}