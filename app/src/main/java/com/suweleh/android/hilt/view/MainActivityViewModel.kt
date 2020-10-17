package com.suweleh.android.hilt.view

import androidx.lifecycle.ViewModel
import com.suweleh.android.hilt.repository.impl.UserRepositoryImpl
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel()
