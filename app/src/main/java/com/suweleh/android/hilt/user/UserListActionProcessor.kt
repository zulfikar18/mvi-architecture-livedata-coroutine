package com.suweleh.android.hilt.user

import androidx.lifecycle.LiveDataScope
import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList

class UserListActionProcessor(
    private val fetchUserList: FetchUserList,
    private val getUserList: GetUserList
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
                    fetchUserList.execute()
                    UserListResult.FetchUserListResult.Success
                },
                failedBlock = {
                    UserListResult.FetchUserListResult.Error(it)
                }
            )
            is UserListAction.GetUserListAction -> result(
                scope = scope,
                initialResult = {
                    UserListResult.GetUserListResult.Loading
                },
                successBlock = {
                    UserListResult.GetUserListResult.Success(
                        getUserList.execute()
                    )
                },
                failedBlock = {
                    UserListResult.GetUserListResult.Error(it)
                }
            )
        }
    }
}
