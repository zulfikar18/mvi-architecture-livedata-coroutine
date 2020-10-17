package com.suweleh.android.hilt.mvi

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel<S : MviViewState, A : MviAction, R : MviResult, E : MviViewEffect> :
    ViewModel() {

    abstract val initialState: S

    abstract val actionProcessor: BaseActionProcessor<A, R>

    private val nextAction = MutableLiveData<A>()

    var viewState: LiveData<S> = MutableLiveData<S>()

    init {
        viewState = Transformations.map(Transformations.switchMap(nextAction) {
            liveData<R> {
                withContext(Dispatchers.IO) {
                    actionProcessor.process(this@liveData, it)
                }
            }
        }) {
            reduce(viewState.value ?: initialState, it)
        }
    }

    fun dispatch(action: A) {
        nextAction.value = action
    }

   abstract fun reduce(currentState: S, result: R): S
}
