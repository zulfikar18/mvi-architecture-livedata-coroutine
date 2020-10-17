package com.suweleh.android.hilt.repository.impl

import com.suweleh.android.hilt.network.ArunaNetworkService
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val arunaNetworkService: ArunaNetworkService
) : UserRepository {
    override suspend fun fetchUserList(): List<UserSchema> {
        return arunaNetworkService.fetchUserList()
    }
}
