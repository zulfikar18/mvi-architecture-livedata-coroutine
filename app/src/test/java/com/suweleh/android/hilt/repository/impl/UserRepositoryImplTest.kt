package com.suweleh.android.hilt.repository.impl

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.suweleh.android.hilt.CoroutineTestRule
import com.suweleh.android.hilt.Dummy
import com.suweleh.android.hilt.db.dao.UserDao
import com.suweleh.android.hilt.network.ArunaNetworkService
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.schema.UserSchema
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.bytebuddy.utility.RandomString
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoException

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(
        TestCoroutineDispatcher()
    )

    @Mock
    private lateinit var mockedArunaNetworkService: ArunaNetworkService

    @Mock
    private lateinit var mockedUserDao: UserDao

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepositoryImpl(
            arunaNetworkService = mockedArunaNetworkService,
            userDao = mockedUserDao
        )
    }

    @Test
    fun `fetchUserList should get value when request api valid`() {
        val expected = Dummy.createListUserSchema()
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedArunaNetworkService.fetchUserList()).thenReturn(expected)
            userRepository.fetchUserList()

            verify(mockedArunaNetworkService).fetchUserList()
        }
    }

    @Test
    fun `fetchUserList should get empty user list when get empty from backend`() {
        val expected = emptyList<UserSchema>()
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedArunaNetworkService.fetchUserList()).thenReturn(expected)
            userRepository.fetchUserList()

            verify(mockedArunaNetworkService).fetchUserList()
        }
    }

    @Test
    fun `fetchUserList should get error when get error from backend`() {
        val expected = MockitoException(RandomString.make(2))
        coroutineTestRule.runBlockingErrorTest(
            testFunction = {
                whenever(mockedArunaNetworkService.fetchUserList()).thenThrow(expected)
                userRepository.fetchUserList()

                verify(mockedArunaNetworkService).fetchUserList()
            },
            verifyFunction = {
                it is MockitoException
            }
        )
    }

    @Test
    fun `getUserList should get list of user when data is available`() {
        val expected = Dummy.createListUserSchema()
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedUserDao.getUserList()).thenReturn(expected.map {
                it.toDao()
            })

            val result = userRepository.getUserList()

            verify(mockedUserDao).getUserList()

            Assert.assertEquals(expected, result)
        }
    }

    @Test
    fun `getUserList should get error when get data from local get something error`() {
        val expected = MockitoException(RandomString.make(2))
        coroutineTestRule.runBlockingErrorTest(
            testFunction = {
                whenever(mockedUserDao.getUserList()).thenThrow(expected)

                userRepository.getUserList()

                verify(mockedUserDao).getUserList()
            },
            verifyFunction = {
                it is MockitoException
            })
    }
}
