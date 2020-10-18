package com.suweleh.android.hilt.db

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.suweleh.android.hilt.Dummy
import com.suweleh.android.hilt.db.dao.UserDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ArunaDbTest {
    private lateinit var arunaDb: ArunaDb
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        arunaDb = ArunaDb.createForTest(InstrumentationRegistry.getInstrumentation().context)
        userDao = arunaDb.userDao()
    }

    @After
    fun tearDown() {
        arunaDb.close()
    }

    @Test
    fun `insertOrReplaceList should insert or replace student`() {
        val userList = Dummy.createListUserSchema().map {
            it.toDao()
        }
        userDao.insertOrReplaceList(userList)

        val result = userDao.getUserList()
        assertEquals(userList, result)

        userDao.insertOrReplaceList(userList)
        val size = userDao.getUserList().size
        assertTrue(userList.size == size)
    }

    @Test
    fun `searchByTitle should show selected user by title`() {
        val userList = listOf(
            Dummy.createUserSchema("A"),
            Dummy.createUserSchema("B"),
            Dummy.createUserSchema("AC"),
            Dummy.createUserSchema("D"),
            Dummy.createUserSchema("EA")
        ).map {
            it.toDao()
        }
        userDao.insertOrReplaceList(userList)
        val result = userDao.getUserByTitle("A")
        assertTrue(result.size == 3)
    }
}
