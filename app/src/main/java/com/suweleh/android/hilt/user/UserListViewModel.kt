package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.mvi.BaseViewModel
import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    fetchUserList: FetchUserList,
    getUserList: GetUserList
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
                    isLoading = false,
                    error = null
                )
                is UserListResult.FetchUserListResult.Loading -> currentState.copy(
                    isLoading = true,
                    error = null
                )
                is UserListResult.FetchUserListResult.Error -> currentState.copy(
                    isLoading = false,
                    error = result.error
                )
            }
            is UserListResult.GetUserListResult -> when (result) {
                is UserListResult.GetUserListResult.Loading -> currentState.copy(
                    isLoading = true,
                    error = null
                )
                is UserListResult.GetUserListResult.Success -> currentState.copy(
                    isLoading = false,
                    list = result.list,
                    error = null
                )
                is UserListResult.GetUserListResult.Error -> currentState.copy(
                    isLoading = false,
                    error = result.error
                )
            }
        }
    }

    override val actionProcessor: BaseActionProcessor<UserListAction, UserListResult> =
        UserListActionProcessor(
            fetchUserList = fetchUserList,
            getUserList = getUserList
        )
}