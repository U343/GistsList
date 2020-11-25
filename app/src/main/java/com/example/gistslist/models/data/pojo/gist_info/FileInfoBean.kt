package com.example.gistslist.models.data.pojo.gist_info

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией о содержимом гиста
 *
 * @param filename название гиста
 * @param type тип содержимого гиста
 * @param language язык программирования в гисте
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */
data class FileInfoBean(
	@SerializedName("filename") val filename: String?,
	@SerializedName("type") val type: String?,
	@SerializedName("language") val language: String?
)