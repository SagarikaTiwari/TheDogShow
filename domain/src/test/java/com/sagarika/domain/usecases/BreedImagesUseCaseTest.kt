package com.sagarika.domain.usecases

import com.sagarika.domain.repository.DogRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BreedImagesUseCaseTest {
    companion object {

        private lateinit var breedImagesUseCase: BreedImagesUseCase
        private val dogRepository = mockk<DogRepository>()
        val breedImagesModel = BreedImagesModel(
            listOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
            ), "success"
        )
    }

    @Before
    fun setUp() {
        breedImagesUseCase = BreedImagesUseCase(dogRepository)
    }

    @Test
    fun `When breedListUseCase is called then it returns Success as Response `() = runTest {
        coEvery {
            breedImagesUseCase("hound")
        } returns Response.Success(breedImagesModel)
        val res = breedImagesUseCase("hound")
        coVerify { breedImagesUseCase("hound") }
        assert(res == Response.Success(breedImagesModel))
    }
}