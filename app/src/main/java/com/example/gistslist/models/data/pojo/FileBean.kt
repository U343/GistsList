package com.example.gistslist.models.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * POJO класс с информацией о содержимом гиста
 *
 * @param filename название гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
data class FileBean(
	@SerializedName("filename") val filename: String?
)