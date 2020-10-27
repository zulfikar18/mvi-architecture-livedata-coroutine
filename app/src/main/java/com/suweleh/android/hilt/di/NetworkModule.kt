package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.network.ApplicationNetworkService
import com.suweleh.android.hilt.network.ApplicationServiceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUserNetworkService(
        okHttpClient: OkHttpClient
    ): ApplicationNetworkService {
        return ApplicationServiceFactory.createService(okHttpClient)
    }
}
