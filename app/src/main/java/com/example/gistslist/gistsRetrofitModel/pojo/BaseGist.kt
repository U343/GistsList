package com.example.gistslist.gistsRetrofitModel.pojo

import com.google.gson.annotations.SerializedName

data class BaseGist(
        @SerializedName("description") val description : String,
        @SerializedName("owner") val owner : Owner
)