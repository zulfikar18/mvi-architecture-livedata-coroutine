package com.suweleh.android.hilt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suweleh.android.hilt.view.MainActivityViewModel
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
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}