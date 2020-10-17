package com.suweleh.android.hilt

import com.suweleh.android.hilt.schema.UserSchema
import net.bytebuddy.utility.RandomString
import kotlin.random.Random

object Dummy {

    fun createUserSchema(): UserSchema {
        return UserSchema(
            userId = Random.nextInt(),
            id = Random.nextInt(),
            title = RandomString.make(2),
            body = RandomString.make(2)
        )
    }

    fun createListUserSchema(): List<UserSchema> {
        return arrayListOf(createUserSchema())
    }
}
