package com.example.gistslist.models.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * POJO с информацией об авторе гиста
 *
 * @param login логин автора гиста
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
data class OwnerBean(
        @SerializedName("login") val login: String
)