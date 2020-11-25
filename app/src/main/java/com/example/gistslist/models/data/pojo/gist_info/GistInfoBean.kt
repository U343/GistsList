package com.example.gistslist.models.data.pojo.gist_info

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией о гисте
 *
 * @param htmlUrl ссылка на гист
 * @param files POJO с информацией об содержимом гиста
 * @param description описание гиста
 * @param ownerInfoBean POJO с информацией об авторе гиста
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */
data class GistInfoBean(
	@SerializedName("html_url") val htmlUrl: String?,
	@SerializedName("files") val files: Map<String, FileInfoBean>,
	@SerializedName("description") val description: String?,
	@SerializedName("owner") val ownerInfoBean: OwnerInfoBean
)