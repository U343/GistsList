package com.example.gistslist.models.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * POJO класс гиста
 *
 * @param files POJO класс с информацией о содержимом гиста
 * @param description описание гиста
 * @param ownerBean POJO с информацией об авторе гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
data class GistBean(
        @SerializedName("files") val files: Map<String, FileBean>,
        @SerializedName("description") val description: String,
        @SerializedName("owner") val ownerBean: OwnerBean
)