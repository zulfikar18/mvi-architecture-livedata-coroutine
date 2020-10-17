package com.suweleh.android.hilt.schema

import com.google.gson.annotations.SerializedName

data class UserSchema(
    @SerializedName("userId") val userId : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("body") val body : String
)
