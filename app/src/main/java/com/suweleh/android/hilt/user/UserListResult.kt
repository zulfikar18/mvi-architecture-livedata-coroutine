package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviResult
import com.suweleh.android.hilt.schema.UserSchema

sealed class UserListResult : MviResult {

    sealed class FetchUserListResult: UserListResult() {
        object Loading: FetchUserListResult()
        data class Success(val isPullToRefresh: Boolean): FetchUserListResult()
        data class Error(val isPullToRefresh: Boolean, val error: Exception): FetchUserListResult()
    }

    sealed class GetUserListResult: UserListResult() {
        object Loading: GetUserListResult()
        data class Success(val list: List<UserSchema>): GetUserListResult()
        data class Error(val error: Exception): GetUserListResult()
    }

    data class SearchByTitleResult(val list: List<UserSchema>): UserListResult()
}
