package com.suweleh.android.hilt.network

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

object HttpClientFactory {
    private const val SHARED_CACHE_SIZE = 1024 * 1024

    fun builder(context: Context, okHttpClientBuilder: OkHttpClient.Builder): Builder {
        return Builder(context, okHttpClientBuilder)
    }

    class Builder constructor(
        private val context: Context,
        private val okHttpClientBuilder: OkHttpClient.Builder
    ) {
        fun sharedCache(): Builder {
            val cacheDir = File(context.cacheDir, "okcache")
            val cache = Cache(cacheDir, SHARED_CACHE_SIZE.toLong())
            okHttpClientBuilder.cache(cache)
            return this
        }

        fun defaults(): Builder {
            okHttpClientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
            return this
        }

        fun addInterceptor(interceptor: Interceptor): Builder {
            okHttpClientBuilder.addInterceptor(interceptor)
            return this
        }

        fun build(): OkHttpClient {
            return okHttpClientBuilder.build()
        }

        fun buildInstance() : OkHttpClient {
            return builder(context, okHttpClientBuilder)
                .defaults()
                .sharedCache()
                .build()
        }

        fun buildInstanceWithInterceptor(interceptor: Interceptor) : OkHttpClient {
            return builder(context, okHttpClientBuilder)
                .defaults()
                .sharedCache()
                .addInterceptor(interceptor)
                .build()
        }
    }
}
