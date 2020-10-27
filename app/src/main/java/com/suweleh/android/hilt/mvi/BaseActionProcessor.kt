package com.suweleh.android.hilt.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseActionProcessor<A : MviAction, R : MviResult> {

    abstract fun process(action: A): Flow<R>

    protected fun result(
        successBlock: suspend () -> R
    ): Flow<R> {
        return flow {
            emit(successBlock())
        }
    }

    protected fun result(
        successBlock: suspend () -> R,
        failedBlock: suspend (Exception) -> R,
        initialResult: () -> R
    ): Flow<R> {
        return flow {
            emit(initialResult())
            try {
                emit(successBlock())
            } catch (error: Exception) {
                emit(failedBlock(error))
            }
        }
    }
}
