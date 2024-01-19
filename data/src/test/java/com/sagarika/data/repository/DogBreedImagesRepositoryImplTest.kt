package com.sagarika.data.repository

import com.sagarika.common.Failure
import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.data.mapper.toDomain
import com.sagarika.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class DogBreedImagesRepositoryTest {
    private lateinit var dogBreedImagesRepository: DogBreedImagesRepositoryImpl
    private lateinit var breedName: String
    private lateinit var subBreedName: String


    private var apiService = mockk<ApiService>()

    private var dogBreedImageApiResponse = mockk<Response<DogBreedImageResponse>>()

    @Before
    fun setUp() {
        breedName = "hound"
        subBreedName = "basset"
        dogBreedImagesRepository = DogBreedImagesRepositoryImpl(apiService)
    }

    @Test
    fun `Given null response when getDogBreedImages is called Then repository should return data error`() =
        runTest {
            every { dogBreedImageApiResponse.body() } returns null
            every { dogBreedImageApiResponse.isSuccessful } returns true
            coEvery { apiService.getBreedImages(breedName) } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogBreedImages(breedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.DataError))
            }
        }

    @Test
    fun `Given response is not successful when getDogBreedImages is called Then repository should return server error`() =
        runTest {
            every { dogBreedImageApiResponse.isSuccessful } returns false
            coEvery { apiService.getBreedImages(breedName) } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogBreedImages(breedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.ServerError))
            }
        }

    @Test
    fun `Given status is failure response when getDogBreedImages is called Then repository should return data error`() =
        runTest {
            val dogBreedImageResponse = DogBreedImageResponse(
                status = "failure",
                message =
                listOf()
            )

            every { dogBreedImageApiResponse.isSuccessful } returns true
            every { dogBreedImageApiResponse.body() } returns dogBreedImageResponse
            coEvery { apiService.getBreedImages(breedName) } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogBreedImages(breedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.DataError))
            }
        }

    @Test
    fun `Given successful response when getDogBreedImages is called Then repository should return breed images list`() =
        runTest {
            val dogBreedImageResponse = DogBreedImageResponse(
                status = "success",
                message =
                listOf(
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
                )
            )

            every { dogBreedImageApiResponse.isSuccessful } returns true
            every { dogBreedImageApiResponse.body() } returns dogBreedImageResponse
            coEvery { apiService.getBreedImages(breedName) } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogBreedImages(breedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Success(dogBreedImageResponse.toDomain()))
            }
        }

    @Test
    fun `Given null response when getDogSubBreedImages is called Then repository should return data error`() =
        runTest {
            every { dogBreedImageApiResponse.body() } returns null
            every { dogBreedImageApiResponse.isSuccessful } returns true
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.DataError))
            }
        }

    @Test
    fun `Given response is not successful when getDogSubBreedImages is called Then repository should return server error`() =
        runTest {
            every { dogBreedImageApiResponse.isSuccessful } returns false
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.ServerError))
            }
        }

    @Test
    fun `Given status is failure response when getDogSubBreedImages is called Then repository should return data error`() =
        runTest {

            val dogBreedImageResponse = DogBreedImageResponse(
                status = "failure",
                message =
                listOf()
            )

            every { dogBreedImageApiResponse.isSuccessful } returns true
            every { dogBreedImageApiResponse.body() } returns dogBreedImageResponse
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Error(Failure.DataError))
            }
        }

    @Test
    fun `Given successful response when getDogSubBreedImages is called Then repository should return breed images list`() =
        runTest {
            val dogBreedImageResponse = DogBreedImageResponse(
                status = "success",
                message =
                listOf(
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                    "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
                )
            )

            every { dogBreedImageApiResponse.isSuccessful } returns true
            every { dogBreedImageApiResponse.body() } returns dogBreedImageResponse
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns dogBreedImageApiResponse

            val dogBreeds = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)
            dogBreeds.collect { a ->
                assert(a == com.sagarika.common.Result.Success(dogBreedImageResponse.toDomain()))
            }
        }
}
