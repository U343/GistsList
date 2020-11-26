package com.example.gistslist.models.data.pojo.gist

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией о гисте
 *
 * @param id идентификатор гиста
 * @param htmlUrl ссылка на гист
 * @param files POJO с информацией об содержимом гиста
 * @param description описание гиста
 * @param ownerInfoBean POJO с информацией об авторе гиста
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */
data class GistBean(
	@SerializedName("id") val id: String,
	@SerializedName("html_url") val htmlUrl: String?,
	@SerializedName("files") val files: Map<String, FileBean>,
	@SerializedName("description") val description: String?,
	@SerializedName("owner") val ownerInfoBean: OwnerBean
)