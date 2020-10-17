package com.suweleh.android.hilt.di

import android.content.Context
import com.suweleh.android.hilt.db.ArunaDb
import com.suweleh.android.hilt.db.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideArunaDb(context: Context): ArunaDb {
        return ArunaDb.create(context)
    }

    @Provides
    fun provideUserDao(arunaDb: ArunaDb): UserDao {
        return arunaDb.userDao()
    }
}
