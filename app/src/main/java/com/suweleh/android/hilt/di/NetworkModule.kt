package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.network.ArunaNetworkService
import com.suweleh.android.hilt.network.ArunaServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @Provides
    fun provideArunaNetworkService(
        okHttpClient: OkHttpClient
    ): ArunaNetworkService {
        return ArunaServiceFactory.createService(okHttpClient)
    }
}
