package com.sagarika.data.repository

import com.sagarika.common.Result
import com.sagarika.data.FakeResponse
import com.sagarika.data.mapper.DogBreedImageResponseToDogBreedImageDTO
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
class DogBreedImagesRepositoryTest {
    private lateinit var dogBreedImagesRepository: DogBreedImagesRepositoryImpl
    private lateinit var breedName: String
    private lateinit var subBreedName: String
    private var apiService = mockk<ApiService>()
    private val dogBreedImageResponseToDogBreedImageDTO =
        mockk<DogBreedImageResponseToDogBreedImageDTO>()

    @Before
    fun setUp() {
        breedName = "hound"
        subBreedName = "basset"
        dogBreedImagesRepository = DogBreedImagesRepositoryImpl(
            apiService,
            Dispatchers.IO,
            dogBreedImageResponseToDogBreedImageDTO
        )
    }

    @Test
    fun `Given exception as response when getDogBreedImages is called Then repository should return data error`() =
        runTest {
            val exception = Exception(ERROR)
            coEvery { apiService.getBreedImages(breedName) } throws exception
            val result = dogBreedImagesRepository.getDogBreedImages(breedName)
            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
         }


    @Test
    fun `Given successful response when getDogBreedImages is called Then repository should return breed images list`() =
        runTest {
            val response = FakeResponse.getBreedImages()
            val mappedResponse = FakeResponse.getBreedImagesMapped()
            coEvery { apiService.getBreedImages(breedName) } returns response
            coEvery { dogBreedImageResponseToDogBreedImageDTO.map(response) } returns mappedResponse
            val res = dogBreedImagesRepository.getDogBreedImages(breedName)
            assert(res == Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(response)))
        }

    @Test
    fun `Given exception as response when getDogSubBreedImages is called Then repository should return data error`() =
        runTest {
            val exception = Exception(ERROR)
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } throws exception

            val result = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)

            assertTrue(result is Result.Error)
            assertEquals(exception, (result as Result.Error).exception)
         }

    @Test
    fun `Given successful response when getDogSubBreedImages is called Then repository should return breed images list`() =
        runTest {
            val response = FakeResponse.getBreedImages()
            val mappedResponse = FakeResponse.getBreedImagesMapped()
            coEvery {
                apiService.fetchDogSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns response

            coEvery { dogBreedImageResponseToDogBreedImageDTO.map(response) } returns mappedResponse

            val result = dogBreedImagesRepository.getDogSubBreedImages(breedName, subBreedName)
            assert(
                result == com.sagarika.common.Result.Success(
                    dogBreedImageResponseToDogBreedImageDTO.map(response)
                )
            )

        }

    private companion object {
        const val ERROR = "Error"
    }
}
