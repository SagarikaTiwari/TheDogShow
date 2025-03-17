package com.sagarika.data.repository

import com.sagarika.common.Result
import com.sagarika.data.FakeResponse
import com.sagarika.data.mapper.DogBreedImageResponseToDogBreedImageDTO
import com.sagarika.data.mapper.DogBreedListResponseToDogBreedListDTO
import com.sagarika.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedRepositoryTest {
    private lateinit var dogBreedImagesRepository: BreedRepositoryImpl
    private lateinit var breedName: String
    private lateinit var subBreedName: String
    private var apiService = mockk<ApiService>()
    private val dogBreedImageResponseToDogBreedImageDTO = mockk<DogBreedImageResponseToDogBreedImageDTO>()
    private var dogBreedListResponseToDogBreedListDTO = mockk<DogBreedListResponseToDogBreedListDTO>()

    @Before
    fun setUp() {
        breedName = "hound"
        subBreedName = "basset"
        dogBreedImagesRepository = BreedRepositoryImpl(
            apiService,
            Dispatchers.IO,
            dogBreedImageResponseToDogBreedImageDTO,
            dogBreedListResponseToDogBreedListDTO,
        )
    }

    @Test
    fun `Given exception as response when getDogBreedImages is called Then repository should return data error`() = runTest {
        val exception = Exception(ERROR)
        coEvery { apiService.getBreedImages(breedName) } throws exception
        val result = dogBreedImagesRepository.getBreedImages(breedName)
        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }

    @Test
    fun `Given successful response when getDogBreedImages is called Then repository should return breed images list`() = runTest {
        val response = FakeResponse.getBreedImages()
        val mappedResponse = FakeResponse.getBreedImagesMapped()
        coEvery { apiService.getBreedImages(breedName) } returns response
        coEvery { dogBreedImageResponseToDogBreedImageDTO.map(response) } returns mappedResponse
        val res = dogBreedImagesRepository.getBreedImages(breedName)
        assert(res == Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(response)))
    }

    @Test
    fun `Given exception as response when getDogSubBreedImages is called Then repository should return data error`() = runTest {
        val exception = Exception(ERROR)
        coEvery {
            apiService.fetchDogSubBreedImages(
                breedName, subBreedName
            )
        } throws exception

        val result = dogBreedImagesRepository.getSubBreedImages(breedName, subBreedName)

        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }

    @Test
    fun `Given successful response when getDogSubBreedImages is called Then repository should return breed images list`() = runTest {
        val response = FakeResponse.getBreedImages()
        val mappedResponse = FakeResponse.getBreedImagesMapped()
        coEvery {
            apiService.fetchDogSubBreedImages(
                breedName, subBreedName
            )
        } returns response

        coEvery { dogBreedImageResponseToDogBreedImageDTO.map(response) } returns mappedResponse

        val result = dogBreedImagesRepository.getSubBreedImages(breedName, subBreedName)
        assert(
            result == Result.Success(
                dogBreedImageResponseToDogBreedImageDTO.map(response)
            )
        )

    }

    @Test
    fun `Given exception in response When getAllBreeds is called Then repository should return error`() = runTest {

        val exception = Exception(ERROR)

        coEvery { apiService.getAllBreeds() } throws exception

        val result = dogBreedImagesRepository.getAllBreeds()

        assertTrue(result is Result.Error)
        assertEquals(exception, (result as Result.Error).exception)
    }

    @Test
    fun `Given success When getAllBreeds is called Then repository should return breed list as response`() = runTest {

        val response = FakeResponse.getAllBreed()
        val mappedResponse = FakeResponse.getAllBreedsMapped()
        coEvery { apiService.getAllBreeds() } returns response
        coEvery { dogBreedListResponseToDogBreedListDTO.map(response) } returns mappedResponse

        val res = dogBreedImagesRepository.getAllBreeds()
        assert(res == Result.Success(dogBreedListResponseToDogBreedListDTO.map(response)))
    }

    private companion object {
        const val ERROR = "Error"
    }
}
