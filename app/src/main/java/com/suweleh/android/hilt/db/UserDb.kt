package com.suweleh.android.hilt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suweleh.android.hilt.db.dao.UserDao
import com.suweleh.android.hilt.db.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = true
)
abstract class UserDb : RoomDatabase() {
    companion object {
        private const val DB_NAME = "userDb"

        fun create(context: Context): UserDb {
            return Room.databaseBuilder(
                context,
                UserDb::class.java, DB_NAME
            ).build()
        }

        fun createForTest(context: Context): UserDb {
            return Room.inMemoryDatabaseBuilder(
                context,
                UserDb::class.java
            ).allowMainThreadQueries().build()
        }
    }

    abstract fun userDao(): UserDao
}
