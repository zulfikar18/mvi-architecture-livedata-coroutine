package com.suweleh.android.hilt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suweleh.android.hilt.user.UserListViewModel
import com.suweleh.android.hilt.viewmodel.ViewModelFactory
import com.suweleh.android.hilt.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: UserListViewModel): ViewModel
}
