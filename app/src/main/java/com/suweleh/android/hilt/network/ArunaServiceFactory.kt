package com.suweleh.android.hilt.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object ArunaServiceFactory {

    fun createService(okHttpClient: OkHttpClient): ArunaNetworkService {
        val builder = Retrofit.Builder()
        builder.baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
        return builder.build().create(ArunaNetworkService::class.java)
    }
}
