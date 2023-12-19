package com.sagarika.domain.usecases

import com.sagarika.domain.repository.DogRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BreedListUseCaseTest {
    companion object {
        private lateinit var breedListUseCase: BreedListUseCase
        private val dogRepository = mockk<DogRepository>()
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
    }

    @Before
    fun setUp() {
        breedListUseCase = BreedListUseCase(dogRepository)
    }

    @Test
    fun `When breedListUseCase is called then it returns Success as Response `() = runTest {
        coEvery {
            dogRepository.getAllBreeds()
        } returns Response.Success(breedsModel)
        val res =breedListUseCase()
        coVerify { dogRepository.getAllBreeds() }
        assert(res == Response.Success(breedsModel))
    }
}