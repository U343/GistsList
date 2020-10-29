package com.example.shoppinglist.gistsRetrofitModel.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Owner(
        @SerializedName("login") val login : String,
        @Expose val id : Int,
        @Expose val node_id : String,
        @Expose val avatar_url : String,
        @Expose val gravatar_id : String,
        @Expose val url : String,
        @Expose val html_url : String,
        @Expose val followers_url : String,
        @Expose val following_url : String,
        @Expose val gists_url : String,
        @Expose val starred_url : String,
        @Expose val subscriptions_url : String,
        @Expose val organizations_url : String,
        @Expose val repos_url : String,
        @Expose val events_url : String,
        @Expose val received_events_url : String,
        @Expose val typeOwner : String,
        @Expose val site_admin : Boolean
)