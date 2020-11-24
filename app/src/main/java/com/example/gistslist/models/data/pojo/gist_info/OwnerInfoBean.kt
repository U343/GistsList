package com.example.gistslist.models.data.pojo.gist_info

import com.google.gson.annotations.SerializedName

data class OwnerInfoBean(
	@SerializedName("login") val login: String?,
	@SerializedName("avatar_url") val avatarUrl: String?
)