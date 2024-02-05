package com.sagarika.data.repository

import com.sagarika.common.Result
import com.sagarika.data.FakeResponse
import com.sagarika.data.mapper.DogBreedListResponseToDogBreedListDTO
import com.sagarika.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class DogBreedsRepositoryTest {

    private lateinit var dogBreedsRepository: DogBreedListRepositoryImpl
    private var apiService = mockk<ApiService>()
    private var dogBreedListResponseToDogBreedListDTO =
        mockk<DogBreedListResponseToDogBreedListDTO>()

    @Before
    fun setUp() {
        dogBreedsRepository = DogBreedListRepositoryImpl(
            apiService,
            Dispatchers.IO,
            dogBreedListResponseToDogBreedListDTO
        )
    }

    @Test
    fun `Given exception in response When getAllBreeds is called Then repository should return error`() =
        runTest {

            val exception = Exception(ERROR)

            coEvery { apiService.getAllBreeds() } throws exception

            val result = dogBreedsRepository.getAllBreeds()

            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
        }


    @Test
    fun `Given success When getAllBreeds is called Then repository should return breed list as response`() =
        runTest {

            val response = FakeResponse.getAllBreed()
            val mappedResponse = FakeResponse.getAllBreedsMapped()
            coEvery { apiService.getAllBreeds() } returns response
            coEvery { dogBreedListResponseToDogBreedListDTO.map(response) } returns mappedResponse

            val res = dogBreedsRepository.getAllBreeds()
            assert(res == Result.Success(dogBreedListResponseToDogBreedListDTO.map(response)))
        }


    private companion object {
        const val ERROR = "Error"
    }
}
