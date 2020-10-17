package com.suweleh.android.hilt.mvi

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope

abstract class BaseActionProcessor<A : MviAction, R : MviResult> {

    abstract suspend fun process(scope: LiveDataScope<R>, action: A)

    abstract val viewModelScope: CoroutineScope

    private val result: MutableLiveData<R> = MutableLiveData()

    protected suspend fun result(
        scope: LiveDataScope<R>,
        successBlock: suspend () -> R,
        failedBlock: (Exception) -> R,
        initialResult: () -> R
    ) {
        scope.emit(initialResult())
        try {
            scope.emit(successBlock())
        } catch (error: Exception) {
            scope.emit(failedBlock(error))
        }
    }
}
