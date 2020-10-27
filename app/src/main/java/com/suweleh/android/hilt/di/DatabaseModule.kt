package com.suweleh.android.hilt.di

import android.content.Context
import com.suweleh.android.hilt.db.ApplicationDb
import com.suweleh.android.hilt.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDb(@ApplicationContext context: Context): ApplicationDb {
        return ApplicationDb.getInstance(context)
    }

    @Provides
    fun provideUserDao(applicationDb: ApplicationDb): UserDao {
        return applicationDb.userDao()
    }
}
