package com.example.gistslist.models.data.pojo.gist_info

import com.google.gson.annotations.SerializedName

data class FileInfoBean(
	@SerializedName("filename") val filename: String?,
	@SerializedName("type") val type: String?,
	@SerializedName("language") val language: String?
)