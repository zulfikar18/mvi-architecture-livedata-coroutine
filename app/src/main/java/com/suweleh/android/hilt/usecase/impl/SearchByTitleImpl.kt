package com.suweleh.android.hilt.usecase.impl

import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import com.suweleh.android.hilt.usecase.SearchByTitle
import javax.inject.Inject

class SearchByTitleImpl @Inject constructor(
    private val userRepository: UserRepository
) : SearchByTitle {
    override suspend fun execute(title: String): List<UserSchema> {
        return userRepository.searchByTitle(title)
    }
}
