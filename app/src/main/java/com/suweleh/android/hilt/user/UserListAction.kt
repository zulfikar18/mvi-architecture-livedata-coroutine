package com.suweleh.android.hilt.user

import com.suweleh.android.hilt.mvi.MviAction

sealed class UserListAction : MviAction {

    object FetchUserListAction: UserListAction()
    object GetUserListAction: UserListAction()
}
