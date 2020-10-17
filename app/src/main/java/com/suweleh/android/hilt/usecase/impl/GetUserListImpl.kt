package com.suweleh.android.hilt.usecase.impl

import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import com.suweleh.android.hilt.usecase.GetUserList
import javax.inject.Inject

class GetUserListImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserList {
    override suspend fun execute(): List<UserSchema> {
        return userRepository.getUserList()
    }
}
