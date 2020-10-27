package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.BaseActionProcessor
import com.suweleh.android.hilt.usecase.FetchUserList
import com.suweleh.android.hilt.usecase.GetUserList
import com.suweleh.android.hilt.usecase.SearchByTitle
import kotlinx.coroutines.flow.Flow

class UserListActionProcessor(
    private val fetchUserList: FetchUserList,
    private val getUserList: GetUserList,
    private val searchByTitle: SearchByTitle
) : BaseActionProcessor<UserListAction, UserListResult>() {

    override fun process(
        action: UserListAction
    ): Flow<UserListResult> {
        return when (action) {
            is UserListAction.FetchUserListAction -> result(
                initialResult = {
                    UserListResult.FetchUserListResult.Loading
                },
                successBlock = {
                    fetchUserList.execute()
                    val userList = getUserList.execute()
                    UserListResult.FetchUserListResult.Success(action.isPullToRefresh, userList)
                },
                failedBlock = {
                    val userList = getUserList.execute()
                    UserListResult.FetchUserListResult.Error(action.isPullToRefresh, userList, it)
                }
            )
            is UserListAction.SearchTitleAction -> result(
                successBlock = {
                    UserListResult.SearchByTitleResult(searchByTitle.execute(action.title))
                }
            )
        }
    }
}
