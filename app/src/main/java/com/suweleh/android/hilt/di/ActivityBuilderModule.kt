package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.user.UserListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): UserListActivity
}
