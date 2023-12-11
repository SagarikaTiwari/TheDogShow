package com.sagarika.data.repository

import com.sagarika.common.Response
import com.sagarika.data.dto.BreedImagesDTO
import com.sagarika.data.dto.Message
import com.sagarika.data.dto.BreedListDTO
import com.sagarika.data.mapper.BreedImagesEntityDataMapper
import com.sagarika.data.mapper.BreedsEntityDataMapper
import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.domain.model.BreedsModel
import com.sagarika.domain.model.MessageModel
import io.mockk.coEvery
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import junitparams.naming.TestCaseName
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(JUnitParamsRunner::class)
class DogRepositoryImplTest {
    private lateinit var dogRepositoryImpl: DogRepositoryImpl
    private val apiService = mockk<ApiService>()
    private val breedsEntityDataMapper = mockk<BreedsEntityDataMapper>()
    private val breedImagesEntityDataMapper = mockk<BreedImagesEntityDataMapper>()
    private companion object {
        val message = Message(
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            listOf("kelpie", "shepherd"), listOf("indian"), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList()
        )
        val breedListDTO = BreedListDTO(message, "success")

        val messageModel = MessageModel(
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            listOf("kelpie", "shepherd"), listOf("indian"), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList(), emptyList(), emptyList(), emptyList(), emptyList(),
            emptyList()
        )

        val breedsModel = BreedsModel(messageModel, "success")

        val breedImagesDTO = BreedImagesDTO(
            listOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
            ), "success"
        )
        val breedImagesModel = BreedImagesModel(
            listOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
            ), "success"
        )
    }

    @Before
    fun setup() {
        dogRepositoryImpl =
            DogRepositoryImpl(apiService, breedsEntityDataMapper, breedImagesEntityDataMapper)
    }

    @Test
    fun `Given api succeeds When getAllBreeds is called Then it returns success as response`() =
        runTest {
            coEvery { apiService.getBreedList() } returns breedListDTO
            coEvery { breedsEntityDataMapper.mapBreedsEntityToBreeds(breedListDTO) } returns breedsModel
            val response = dogRepositoryImpl.getAllBreeds()
            assert(response == Response.Success(breedsModel))
        }

    @Test
    @Parameters(method = "testData")
    @TestCaseName("Given api throws {0} When getAllBreeds is called Then it returns {1}")
    fun `Given api throws exception When getAllBreeds is called Then it returns error`(
        e: Exception,
        errorState: Boolean
    ) =
        runTest {
            coEvery { apiService.getBreedList() } throws e
            val response = dogRepositoryImpl.getAllBreeds()
            assert(response is Response.Error).equals(errorState)
        }

    @Test
    fun `Given api succeeds When getBreedImages is called then it returns success as response`() =
        runTest {

            coEvery { apiService.getBreedImages("hound") } returns breedImagesDTO
            coEvery { breedImagesEntityDataMapper.mapBreedImagesEntityToBreedImages(breedImagesDTO) } returns breedImagesModel
            val response = dogRepositoryImpl.getBreedImages("hound")
            assert(response == Response.Success(breedImagesModel))
        }

    @Test
    @Parameters(method = "testData")
    @TestCaseName("Given api throws {0} When getBreedImages is called Then it returns {1}")
    fun `Given api throws exception When getBreedImages is called Then it returns error`(
        e: Exception,
        errorState: Boolean
    ) =
        runTest {
            coEvery { apiService.getBreedImages("hound") } throws e
            val response = dogRepositoryImpl.getAllBreeds()
            assert(response is Response.Error).equals(errorState)
        }

    private fun testData() = arrayOf(
        arrayOf(Exception("An error"), true),
        arrayOf(IOException("IO Exception"), true)
    )
}
