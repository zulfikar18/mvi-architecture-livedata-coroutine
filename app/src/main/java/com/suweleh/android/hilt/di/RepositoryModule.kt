package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
