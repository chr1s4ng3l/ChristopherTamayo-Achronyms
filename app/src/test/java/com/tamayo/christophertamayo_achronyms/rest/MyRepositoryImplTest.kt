package com.tamayo.christophertamayo_achronyms.rest


import com.tamayo.christophertamayo_achronyms.data.model.AcronymsItem
import com.tamayo.christophertamayo_achronyms.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AcronymRepositoryImplTest {

    private lateinit var tesObject: MyRepository
    private val mockServiceApi = mockk<ServiceAPI>(relaxed = true)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)


    @Before
    fun setUp() {
        tesObject = MyRepositoryImpl(mockServiceApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `get meaning when the server retrieve the long form from the short returns a success state`() {
        //AAA

        //Given

        coEvery {
            mockServiceApi.getAcronyms("HMM")

            //When
        } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns listOf(
                AcronymsItem(
                    listOf(
                        mockk(relaxed = true)
                    )
                )

            )

        }


        val state = mutableListOf<UIState<List<AcronymsItem>>>()
        val job = testScope.launch {
            tesObject.getAllAcronyms("HMM").collect {
                state.add(it)
            }
        }

        //Then
        assertEquals(2, state.size)
        val success = (state[1] as UIState.SUCCESS).response
        assertEquals(1, success.size)

        coEvery { mockServiceApi.getAcronyms("HMM") }
        job.cancel()

    }
}