package com.suweleh.android.hilt.mvi

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

abstract class BaseViewModel<S : MviViewState, A : MviAction, R : MviResult, E : MviViewEffect> :
    ViewModel() {

    abstract val initialState: S

    abstract val actionProcessor: BaseActionProcessor<A, R>

    private val nextAction = MutableLiveData<A>()

    var viewState: LiveData<S> = MutableLiveData()

    init {
        viewState = Transformations.map(Transformations.switchMap(nextAction) {
            actionProcessor.process(it).flowOn(Dispatchers.IO).asLiveData(Dispatchers.IO)
        }) {
            reduce(viewState.value ?: initialState, it)
        }.distinctUntilChanged()
    }

    fun dispatch(action: A) {
        nextAction.value = action
    }

    abstract fun reduce(currentState: S, result: R): S
}
