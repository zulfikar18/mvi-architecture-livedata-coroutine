package com.suweleh.android.hilt.usecase.impl

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.suweleh.android.hilt.CoroutineTestRule
import com.suweleh.android.hilt.Dummy
import com.suweleh.android.hilt.repository.UserRepository
import com.suweleh.android.hilt.usecase.SearchByTitle
import junit.framework.TestCase
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import net.bytebuddy.utility.RandomString
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchByTitleImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(
        TestCoroutineDispatcher()
    )

    @Mock
    private lateinit var mockedUserRepository: UserRepository

    private lateinit var searchByTitle: SearchByTitle

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        searchByTitle = SearchByTitleImpl(mockedUserRepository)
    }

    @Test
    fun `execute should result value when found data from local db`() {
        val expectation = Dummy.createListUserSchema()
        val title = RandomString.make(2)
        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(mockedUserRepository.searchByTitle(any())).thenReturn(expectation)
            val result = searchByTitle.execute(title)
            verify(mockedUserRepository).searchByTitle(title)
            Assert.assertEquals(expectation, result)
        }
    }
}
