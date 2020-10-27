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
abstract class ApplicationDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "userDb"

        @Volatile private var instance: ApplicationDb? = null

        fun getInstance(context: Context): ApplicationDb {
            return instance ?: synchronized(this) {
                instance ?: create(context).also { instance = it }
            }
        }

        fun create(context: Context): ApplicationDb {
            return Room.databaseBuilder(
                context,
                ApplicationDb::class.java, DB_NAME
            ).build()
        }

        fun createForTest(context: Context): ApplicationDb {
            return Room.inMemoryDatabaseBuilder(
                context,
                ApplicationDb::class.java
            ).allowMainThreadQueries().build()
        }
    }
}
