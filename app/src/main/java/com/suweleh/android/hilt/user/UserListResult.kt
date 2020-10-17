package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviResult
import com.suweleh.android.hilt.schema.UserSchema

sealed class UserListResult : MviResult {

    sealed class FetchUserListResult: UserListResult() {
        object Loading: FetchUserListResult()
        data class Success(val list: List<UserSchema>): FetchUserListResult()
        data class Error(val error: Exception): FetchUserListResult()
    }
}
