package com.suweleh.android.hilt.repository

import com.suweleh.android.hilt.schema.UserSchema

interface UserRepository {

    suspend fun fetchUserList()

    suspend fun getUserList(): List<UserSchema>
}
