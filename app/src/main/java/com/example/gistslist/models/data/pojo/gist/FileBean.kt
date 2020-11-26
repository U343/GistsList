package com.example.gistslist.models.data.pojo.gist

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией о содержимом гиста
 *
 * @param filename название гиста
 * @param type тип содержимого гиста
 * @param language язык программирования в гисте
 * @param content содержимое гиста
 *
 * @author Dmitrii Bondarev on 25.11.2020
 */
data class FileBean(
	@SerializedName("filename") val filename: String?,
	@SerializedName("type") val type: String?,
	@SerializedName("language") val language: String?,
	@SerializedName("content") val content: String?
)