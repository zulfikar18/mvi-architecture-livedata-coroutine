package com.suweleh.android.hilt.user

import androidx.lifecycle.LiveDataScope
import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.usecase.FetchUserList
import kotlinx.coroutines.CoroutineScope

class UserListActionProcessor(
    override val viewModelScope: CoroutineScope,
    private val fetchUserList: FetchUserList
) : BaseActionProcessor<UserListAction, UserListResult>() {

    override suspend fun process(
        scope: LiveDataScope<UserListResult>,
        action: UserListAction
    ) {
        when (action) {
            is UserListAction.FetchUserListAction -> result(
                scope = scope,
                initialResult = {
                    UserListResult.FetchUserListResult.Loading
                },
                successBlock = {
                    UserListResult.FetchUserListResult.Success(
                        fetchUserList.execute()
                    )
                },
                failedBlock = {
                    UserListResult.FetchUserListResult.Error(it)
                }
            )
        }
    }
}
