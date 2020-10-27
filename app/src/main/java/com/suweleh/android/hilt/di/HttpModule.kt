package com.suweleh.android.hilt.di

import android.content.Context
import com.suweleh.android.hilt.network.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class HttpModule {

    @Provides
    fun providesOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context, okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return HttpClientFactory.builder(
            context, okHttpClientBuilder
        ).buildInstance()
    }
}
