package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.BreedRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedImagesUseCaseTest {
    private lateinit var getDogBreedImagesUseCase: BreedImagesUseCase

    private var breedRepository = mockk<BreedRepository>()

    @Before
    fun setUp() {
        getDogBreedImagesUseCase = BreedImagesUseCase(breedRepository)
    }

    @Test
    fun `Given repository returns success When breedImagesusecase is called Then invoke function should get called from repository`() =
        runTest {
            val breedName = "hound"
            coEvery { breedRepository.getBreedImages(breedName) } returns
                    Result.Success(emptyList<DogBreedImage>())


            getDogBreedImagesUseCase(breedName)
            coVerify(exactly = 1) { breedRepository.getBreedImages(breedName) }
        }
}
