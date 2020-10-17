package com.suweleh.android.hilt.user

import androidx.lifecycle.viewModelScope
import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.mvi.BaseViewModel
import com.suweleh.android.hilt.usecase.FetchUserList
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    fetchUserList: FetchUserList
) : BaseViewModel<UserListViewState, UserListAction, UserListResult, UserListViewEffect>() {

    override val initialState: UserListViewState
        get() = UserListViewState.initialData()

    override fun reduce(
        currentState: UserListViewState,
        result: UserListResult
    ): UserListViewState {
        return when (result) {
            is UserListResult.FetchUserListResult -> when (result) {
                is UserListResult.FetchUserListResult.Success -> currentState.copy(
                    list = result.list
                )
                else -> {
                    currentState
                }
            }
        }
    }

    override val actionProcessor: BaseActionProcessor<UserListAction, UserListResult> =
        UserListActionProcessor(
            viewModelScope = viewModelScope,
            fetchUserList = fetchUserList
        )
}
