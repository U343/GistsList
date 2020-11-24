package com.example.gistslist.models.presentation.gist_model

data class GistInfoModel(
	val gistName: String?,
	val gistType: String?,
	val gistLanguage: String?,
	val urlToGist: String?,
	val authorLogin: String?,
	val avatarUrl: String?,
	val gistDescription: String?
)