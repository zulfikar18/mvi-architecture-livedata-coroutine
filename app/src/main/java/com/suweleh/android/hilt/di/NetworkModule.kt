package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.network.UserNetworkService
import com.suweleh.android.hilt.network.UserServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @Provides
    fun provideUserNetworkService(
        okHttpClient: OkHttpClient
    ): UserNetworkService {
        return UserServiceFactory.createService(okHttpClient)
    }
}
