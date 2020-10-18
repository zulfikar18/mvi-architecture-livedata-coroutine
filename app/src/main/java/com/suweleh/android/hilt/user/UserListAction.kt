package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviAction

sealed class UserListAction : MviAction {

    data class FetchUserListAction(val isPullToRefresh: Boolean): UserListAction()
    object GetUserListAction: UserListAction()
    data class SearchTitleAction(val title: String): UserListAction()
}
