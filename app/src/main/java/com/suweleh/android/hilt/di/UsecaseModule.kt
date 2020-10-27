package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList
import com.suweleh.android.hilt.usecase.SearchByTitle
import com.suweleh.android.hilt.usecase.impl.FetchUserListImpl
import com.suweleh.android.hilt.usecase.impl.GetUserListImpl
import com.suweleh.android.hilt.usecase.impl.SearchByTitleImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class UsecaseModule {

    @Binds
    abstract fun bindsFetchUserList(fetchUserListImpl: FetchUserListImpl): FetchUserList

    @Binds
    abstract fun bindsGetUserList(getUserList: GetUserListImpl): GetUserList

    @Binds
    abstract fun bindSearchByTitle(searchByTitleImpl: SearchByTitleImpl): SearchByTitle
}
