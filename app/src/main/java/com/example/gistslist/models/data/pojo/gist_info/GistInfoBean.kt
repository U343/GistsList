package com.example.gistslist.models.data.pojo.gist_info

import com.google.gson.annotations.SerializedName

data class GistInfoBean(
	@SerializedName("html_url") val htmlUrl: String?,
	@SerializedName("files") val files: Map<String, FileInfoBean>,
	@SerializedName("description") val description: String?,
	@SerializedName("owner") val ownerBean: OwnerInfoBean
)