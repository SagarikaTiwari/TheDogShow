package com.sagarika.data.repository

import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.data.mapper.toDomain
import com.sagarika.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class DogBreedsRepositoryTest {

    private lateinit var dogBreedsRepository: DogBreedListRepositoryImpl
    private var apiService = mockk<ApiService>()
    private var dogBreedApiResponse = mockk<Response<DogBreedResponse>>()

    @Before
    fun setUp() {
        dogBreedsRepository = DogBreedListRepositoryImpl(apiService)
    }

    @Test
    fun `Given null response When getAllBreeds is called Then repository should return data error`() = runBlockingTest {
        every { dogBreedApiResponse.body() } returns null
        every { dogBreedApiResponse.isSuccessful } returns true
        coEvery { apiService.getAllBreeds() } returns dogBreedApiResponse

        val dogBreeds = dogBreedsRepository.getAllBreeds()
        dogBreeds.collect { res ->
            assert(res == com.sagarika.common.Result.Error(Failure.DataError))
        }
    }

    @Test
    fun`Given server error response When getAllBreeds is called Then repository should return server error`() =
        runBlockingTest {
            every { dogBreedApiResponse.isSuccessful } returns false
            coEvery { apiService.getAllBreeds() } returns dogBreedApiResponse

            val dogBreeds = dogBreedsRepository.getAllBreeds()
            dogBreeds.collect { res ->
                assert(res == com.sagarika.common.Result.Error(Failure.ServerError))
            }
        }

    @Test

    fun `Given status is not success When getAllBreeds is called Then repository should return data error`() = runBlockingTest {
        val dogBreedResponse = DogBreedResponse(
            status = "failure",
            message =
            mapOf()
        )

        every { dogBreedApiResponse.isSuccessful } returns true
        every { dogBreedApiResponse.body() } returns dogBreedResponse
        coEvery { apiService.getAllBreeds() } returns dogBreedApiResponse

        val dogBreeds = dogBreedsRepository.getAllBreeds()
        dogBreeds.collect { res ->
            assert(res == com.sagarika.common.Result.Error(Failure.DataError))
        }
    }

    @Test
    fun `Given success When getAllBreeds is called Then repository should return breed list as response`() = runBlockingTest {
        val dogBreedResponse = DogBreedResponse(
            status = "success",
            message =
            mapOf(
                "hound" to listOf("afghan", "basset"),
                "australian" to listOf("shepherd")
            )
        )

        every { dogBreedApiResponse.isSuccessful } returns true
        every { dogBreedApiResponse.body() } returns dogBreedResponse
        coEvery { apiService.getAllBreeds() } returns dogBreedApiResponse

        val dogBreeds = dogBreedsRepository.getAllBreeds()
        dogBreeds.collect { res ->
            assert(res == Result.Success(dogBreedResponse.toDomain()))
        }
    }
}
