package com.suweleh.android.hilt.usecase.impl

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.suweleh.android.hilt.CoroutineTestRule
import com.suweleh.android.hilt.Dummy
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.usecase.FetchUserList
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
class FetchUserListImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(
        TestCoroutineDispatcher()
    )

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var fetchUserList: FetchUserList

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        fetchUserList = FetchUserListImpl(userRepository)
    }

    @Test
    fun `execute should return value when get value from service`() {
        val expected = Dummy.createListUserSchema()
        coroutineTestRule.testDispatcher.runBlockingTest {
            fetchUserList.execute()
            verify(userRepository).fetchUserList()
        }
    }

    @Test
    fun `execute should return error when get invalid value from service`() {
        val expected =MockitoException(RandomString.make(2))
        coroutineTestRule.runBlockingErrorTest(
            testFunction = {
                whenever(userRepository.fetchUserList()).thenThrow(expected)
                fetchUserList.execute()
            },
            verifyFunction = {
                it is MockitoException
            }
        )
    }
}
