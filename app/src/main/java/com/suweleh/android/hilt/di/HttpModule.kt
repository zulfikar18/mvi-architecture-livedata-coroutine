package com.suweleh.android.hilt.di

import android.content.Context
import com.suweleh.android.hilt.network.HttpClientFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class HttpModule {

    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    fun provideOkHttpClient(context: Context, okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return HttpClientFactory.builder(
            context, okHttpClientBuilder
        ).buildInstance()
    }
}
