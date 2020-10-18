package com.suweleh.android.hilt.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suweleh.android.hilt.db.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE title LIKE '%' || :title || '%'")
    fun getUserByTitle(vararg title: String): List<UserEntity>

    @Query("SELECT * FROM users")
    fun getUserList(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceList(data: List<UserEntity>)
}
