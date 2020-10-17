package com.suweleh.android.hilt.di

import com.suweleh.android.hilt.user.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindFragmentUserList(): UserListFragment
}
