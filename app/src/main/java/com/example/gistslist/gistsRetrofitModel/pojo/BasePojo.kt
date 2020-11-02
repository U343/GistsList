package com.example.gistslist.gistsRetrofitModel.pojo

import com.google.gson.annotations.SerializedName

data class BasePojo(
        @SerializedName("files") val files : Map<String, Filename>,
        @SerializedName("description") val description : String,
        @SerializedName("owner") val owner : Owner
)