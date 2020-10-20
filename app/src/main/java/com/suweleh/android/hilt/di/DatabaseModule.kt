package com.suweleh.android.hilt.di

import android.content.Context
import com.suweleh.android.hilt.db.UserDb
import com.suweleh.android.hilt.db.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideUserDb(context: Context): UserDb {
        return UserDb.create(context)
    }

    @Provides
    fun provideUserDao(userDb: UserDb): UserDao {
        return userDb.userDao()
    }
}
