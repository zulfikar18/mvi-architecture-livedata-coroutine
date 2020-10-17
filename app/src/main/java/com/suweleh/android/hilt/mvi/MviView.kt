package com.suweleh.android.hilt.mvi

interface MviView<S : MviViewState> {

    fun render(state: S)
}
