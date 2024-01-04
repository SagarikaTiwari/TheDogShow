package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
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
class DogSubBreedUseCaseTest {
    private lateinit var getDogSubBreedImagesUseCase: DogSubBreedUseCase

    private var dogBreedImagesRepository = mockk<DogBreedImagesRepository>()

    @Before
    fun setUp() {
        getDogSubBreedImagesUseCase = DogSubBreedUseCase(dogBreedImagesRepository)
    }

    @Test
    fun `Given repository returns success When dogSubBreedUseCase is called Then invoke function should get called from repository`() = runTest {
            val dogSubBreedParams = DogSubBreedUseCase.DogSubBreedParams("hound", "afgan")
            coEvery {
                dogBreedImagesRepository.getDogSubBreedImages(
                    dogSubBreedParams.breedName,
                    dogSubBreedParams.subBreedName
                )
            } returns flow {
                emit(
                    Result.Success(emptyList<DogBreedImage>())
                )
            }
            getDogSubBreedImagesUseCase(dogSubBreedParams)
            coVerify(exactly = 1) {
                dogBreedImagesRepository.getDogSubBreedImages(
                    dogSubBreedParams.breedName,
                    dogSubBreedParams.subBreedName
                )
            }
        }
}
