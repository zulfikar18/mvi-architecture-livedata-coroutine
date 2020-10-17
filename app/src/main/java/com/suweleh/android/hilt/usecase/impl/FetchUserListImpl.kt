package com.suweleh.android.hilt.usecase.impl

import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import com.suweleh.android.hilt.usecase.FetchUserList
import javax.inject.Inject

class FetchUserListImpl @Inject constructor(
    private val userRepository: UserRepository
) : FetchUserList {
    override suspend fun fetchUserList(): List<UserSchema> {
        TODO("Not yet implemented")
    }
}
