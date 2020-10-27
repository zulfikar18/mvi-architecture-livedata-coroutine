package com.suweleh.android.hilt.repository.impl

import com.suweleh.android.hilt.db.dao.UserDao
import com.suweleh.android.hilt.network.ApplicationNetworkService
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val applicationNetworkService: ApplicationNetworkService,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun fetchUserList() {
        applicationNetworkService.fetchUserList().map {
            it.toDao()
        }.let {
            userDao.insertOrReplaceList(it)
        }
    }

    override suspend fun getUserList(): List<UserSchema> {
        return userDao.getUserList().map {
            UserSchema.fromDao(it)
        }
    }

    override suspend fun searchByTitle(title: String): List<UserSchema> {
        return userDao.getUserByTitle(title).map {
            UserSchema.fromDao(it)
        }
    }
}
