package com.suweleh.android.hilt.network

import com.suweleh.android.hilt.schema.UserSchema
import retrofit2.http.GET

interface ApplicationNetworkService {

    @GET("posts")
    suspend fun fetchUserList(): List<UserSchema>
}
