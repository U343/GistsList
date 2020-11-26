package com.example.gistslist.models.data.pojo.gist

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией об авторе гиста
 *
 * @param login логин автора гиста
 * @param avatarUrl ссылка на аватар автора гиста
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */
data class OwnerBean(
	@SerializedName("login") val login: String?,
	@SerializedName("avatar_url") val avatarUrl: String?
)