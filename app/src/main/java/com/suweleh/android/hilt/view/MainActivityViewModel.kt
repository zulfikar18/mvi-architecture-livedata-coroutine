package com.suweleh.android.hilt.view

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(): ViewModel() {

    fun getText(): String {
        return "Halo izul"
    }
}