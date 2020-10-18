package com.suweleh.android.hilt.usecase

import com.suweleh.android.hilt.schema.UserSchema

interface SearchByTitle {

    suspend fun execute(title: String): List<UserSchema>
}
