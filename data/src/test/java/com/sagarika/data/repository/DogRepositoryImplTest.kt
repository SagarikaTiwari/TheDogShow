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
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

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
    fun `When getAllBreeds is called Given api succeeds Then it returns success as response`() =
        runTest {
            coEvery { apiService.getBreedList() } returns breedListDTO
            coEvery { breedsEntityDataMapper.mapBreedsEntityToBreeds(breedListDTO) } returns breedsModel
            val response = dogRepositoryImpl.getAllBreeds()
            assert(response == Response.Success(breedsModel))
        }

    @Test
    fun `When getBreedImages is called then it returns success as response`() =
        runTest {

            coEvery { apiService.getBreedImages("hound") } returns breedImagesDTO
            coEvery { breedImagesEntityDataMapper.mapBreedImagesEntityToBreedImages(breedImagesDTO) } returns breedImagesModel
            val response = dogRepositoryImpl.getBreedImages("hound")
            assert(response == Response.Success(breedImagesModel))
        }

}
