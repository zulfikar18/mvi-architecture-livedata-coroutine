package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviViewState
import com.suweleh.android.hilt.schema.UserSchema

data class UserListViewState(
    val isLoading: Boolean,
    val list: List<UserSchema>,
    val error: Exception?
): MviViewState {
    companion object {
        fun initialData(): UserListViewState {
            return UserListViewState(
                isLoading = false,
                list = emptyList(),
                error = null
            )
        }
    }
}
