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
abstract class ArunaDb : RoomDatabase() {
    companion object {
        private const val DB_NAME = "arunaDb"

        fun create(context: Context): ArunaDb {
            return Room.databaseBuilder(
                context,
                ArunaDb::class.java, DB_NAME
            ).build()
        }

        fun createForTest(context: Context): ArunaDb {
            return Room.inMemoryDatabaseBuilder(
                context,
                ArunaDb::class.java
            ).allowMainThreadQueries().build()
        }
    }

    abstract fun userDao(): UserDao
}
