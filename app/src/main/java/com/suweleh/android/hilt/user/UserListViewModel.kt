package com.suweleh.android.hilt.user

import androidx.hilt.lifecycle.ViewModelInject
import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.mvi.BaseViewModel
import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList
import com.suweleh.android.hilt.usecase.SearchByTitle

class UserListViewModel @ViewModelInject constructor(
    fetchUserList: FetchUserList,
    getUserList: GetUserList,
    searchByTitle: SearchByTitle
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
                    error = null,
                    list = result.list,
                    isPullToRefresh = result.isPullToRefresh
                )
                is UserListResult.FetchUserListResult.Loading -> currentState.copy(
                    isLoading = true,
                    error = null
                )
                is UserListResult.FetchUserListResult.Error -> currentState.copy(
                    isLoading = false,
                    error = result.error,
                    list = result.list,
                    isPullToRefresh = result.isPullToRefresh
                )
            }
            is UserListResult.SearchByTitleResult -> currentState.copy(
                isLoading = false,
                error = null,
                list = result.list
            )
        }
    }

    override val actionProcessor: BaseActionProcessor<UserListAction, UserListResult> =
        UserListActionProcessor(
            fetchUserList = fetchUserList,
            getUserList = getUserList,
            searchByTitle = searchByTitle
        )
}
