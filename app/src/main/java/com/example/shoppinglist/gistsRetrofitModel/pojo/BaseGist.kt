package com.example.shoppinglist.gistsRetrofitModel.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseGist(
        @Expose val url : String,
        @Expose val forks_url : String,
        @Expose val commits_url : String,
        @Expose val id : String,
        @Expose val node_id : String,
        @Expose val git_pull_url : String,
        @Expose val git_push_url : String,
        @Expose val html_url : String,
        @Expose val files : String,
        @Expose val public : Boolean,
        @Expose val created_at : String,
        @Expose val updated_at : String,
        @SerializedName("description") val description : String,
        @Expose val comments : Int,
        @Expose val user : String,
        @Expose val comments_url : String,
        @SerializedName("owner") val owner : Owner,
        @Expose val truncated : Boolean
)