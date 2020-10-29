package com.example.shoppinglist.retrofit

data class GistModel(
		val id: Long,
		val userLogin: String,
		val gistFilename: String
)

data class ResultGists(
		val total_count: Int,
		val incomplete_results: Boolean,
		val items: List<GistModel>
)