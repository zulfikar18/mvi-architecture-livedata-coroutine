package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviResult
import com.suweleh.android.hilt.schema.UserSchema

sealed class UserListResult : MviResult {

    sealed class FetchUserListResult: UserListResult() {
        object Loading: FetchUserListResult()
        data class Success(val isPullToRefresh: Boolean, val list: List<UserSchema>): FetchUserListResult()
        data class Error(val isPullToRefresh: Boolean, val list: List<UserSchema>, val error: Exception): FetchUserListResult()
    }

    data class SearchByTitleResult(val list: List<UserSchema>): UserListResult()
}
