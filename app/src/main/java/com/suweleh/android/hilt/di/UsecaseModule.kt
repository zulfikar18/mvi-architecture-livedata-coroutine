package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList
import com.suweleh.android.hilt.usecase.impl.FetchUserListImpl
import com.suweleh.android.hilt.usecase.impl.GetUserListImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UsecaseModule {

    @Binds
    abstract fun bindsFetchUserList(fetchUserListImpl: FetchUserListImpl): FetchUserList

    @Binds
    abstract fun bindsGetUserList(getUserList: GetUserListImpl): GetUserList
}
