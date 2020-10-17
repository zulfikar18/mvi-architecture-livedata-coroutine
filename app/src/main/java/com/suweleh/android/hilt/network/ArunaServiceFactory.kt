package com.suweleh.android.hilt.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArunaServiceFactory {

    fun createService(okHttpClient: OkHttpClient): ArunaNetworkService {
        val builder = Retrofit.Builder()
        builder.baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
        return builder.build().create(ArunaNetworkService::class.java)
    }
}
