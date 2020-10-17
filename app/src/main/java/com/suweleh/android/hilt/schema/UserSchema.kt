package com.suweleh.android.hilt.schema

import com.suweleh.android.hilt.db.entity.UserEntity

data class UserSchema(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {

    companion object {
        fun fromDao(entity: UserEntity): UserSchema {
            return UserSchema(
                userId = entity.userId,
                id = entity.id,
                title = entity.title,
                body = entity.body
            )
        }
    }

    fun toDao(): UserEntity {
        return UserEntity(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }

}
