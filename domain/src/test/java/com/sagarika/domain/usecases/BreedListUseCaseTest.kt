package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.repository.DogBreedRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedListUseCaseTest {

    private lateinit var getDogBreedsUseCase: BreedListUseCase


    private var dogBreedsRepository = mockk<DogBreedRepository>()

    @Before
    fun setUp() {
        getDogBreedsUseCase = BreedListUseCase(dogBreedsRepository)
    }

    @Test
    fun `Given repository returns success When breedlistusecase is called Then invoke function should get called from repository`() =
        runTest {
            coEvery { dogBreedsRepository.getAllBreeds() } returns
                    Result.Success(emptyList<DogBreed>())

            getDogBreedsUseCase()
            coVerify(exactly = 1) { dogBreedsRepository.getAllBreeds() }
        }
}