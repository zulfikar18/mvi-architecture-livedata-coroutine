package com.suweleh.android.hilt.usecase.impl

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.suweleh.android.hilt.CoroutineTestRule
import com.suweleh.android.hilt.Dummy
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.usecase.GetUserList
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

class GetUserListImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(
        TestCoroutineDispatcher()
    )

    @Mock
    private lateinit var mockedUserRepository: UserRepository

    private lateinit var getUserList: GetUserList

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getUserList = GetUserListImpl(mockedUserRepository)
    }

    @Test
    fun `execute should get data when data available`() {
        val expected = Dummy.createListUserSchema()
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedUserRepository.getUserList()).thenReturn(expected)

            val result = getUserList.execute()

            verify(mockedUserRepository).getUserList()

            Assert.assertEquals(expected, result)
        }
    }

    @Test
    fun `execute should get error when data not available`() {
        val expected = MockitoException(RandomString.make(2))
        coroutineTestRule.runBlockingErrorTest(
            testFunction = {
                whenever(mockedUserRepository.getUserList()).thenThrow(expected)

                getUserList.execute()
            },
            verifyFunction = {
                it is MockitoException
            })
    }
}
