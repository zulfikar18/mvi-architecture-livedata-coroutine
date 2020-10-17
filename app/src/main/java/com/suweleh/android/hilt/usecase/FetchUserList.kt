package com.suweleh.android.hilt.usecase

import com.suweleh.android.hilt.schema.UserSchema

interface FetchUserList {

    suspend fun execute(): List<UserSchema>
}
