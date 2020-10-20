package com.suweleh.android.hilt.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserServiceFactory {

    fun createService(okHttpClient: OkHttpClient): UserNetworkService {
        val builder = Retrofit.Builder()
        builder.baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
        return builder.build().create(UserNetworkService::class.java)
    }
}
