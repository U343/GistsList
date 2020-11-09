package com.example.gistslist.models.data.pojo

import com.google.gson.annotations.SerializedName

data class GistBean(
        @SerializedName("files") val files: Map<String, FileBean>,
        @SerializedName("description") val description: String,
        @SerializedName("owner") val ownerBean: OwnerBean
)