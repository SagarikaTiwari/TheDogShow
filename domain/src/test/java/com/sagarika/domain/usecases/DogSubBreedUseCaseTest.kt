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
class DogSubBreedUseCaseTest {
    private lateinit var getDogSubBreedImagesUseCase: DogSubBreedUseCase

    private var breedRepository = mockk<BreedRepository>()

    @Before
    fun setUp() {
        getDogSubBreedImagesUseCase = DogSubBreedUseCase(breedRepository)
    }

    @Test
    fun `Given repository returns success When dogSubBreedUseCase is called Then invoke function should get called from repository`() =
        runTest {
            val breedName = "hound"
            val subBreedName = "afgan"
            coEvery {
                breedRepository.getSubBreedImages(
                    breedName,
                    subBreedName
                )
            } returns
                    Result.Success(emptyList<DogBreedImage>())

            getDogSubBreedImagesUseCase(breedName, subBreedName)
            coVerify(exactly = 1) {
                breedRepository.getSubBreedImages(
                    breedName,
                    subBreedName
                )
            }
        }
}
