package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedImagesUseCaseTest {
    private lateinit var getDogBreedImagesUseCase: BreedImagesUseCase

    private var dogBreedImagesRepository = mockk<DogBreedImagesRepository>()

    @Before
    fun setUp() {
        getDogBreedImagesUseCase = BreedImagesUseCase(dogBreedImagesRepository)
    }

    @Test
    fun `Given repository returns success When breedImagesusecase is called Then invoke function should get called from repository`() =
        runTest {
            val breedName = "hound"
            coEvery { dogBreedImagesRepository.getDogBreedImages(breedName) } returns
                    Result.Success(emptyList<DogBreedImage>())


            getDogBreedImagesUseCase(breedName)
            coVerify(exactly = 1) { dogBreedImagesRepository.getDogBreedImages(breedName) }
        }
}
