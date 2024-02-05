package com.sagarika.features.presentation.ui.breedgallery

import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.DogSubBreedUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.constants.servererrorMsg
import com.sagarika.features.presentation.mapper.DogBreedImageDomainToDogBreedImagePresentation
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.breedgallery.fakes.FakeGalleryData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedGalleryViewModelTest {

    private lateinit var breedGalleryViewModel: BreedGalleryViewModel
    private var breedImagesUseCase = mockk<BreedImagesUseCase>()
    private var subBreedUseCase = mockk<DogSubBreedUseCase>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var breedName: String
    private lateinit var subBreedName: String
    private lateinit var dogSubBreedParams: DogSubBreedUseCase.DogSubBreedParams
    private val dogBreedImageDomainToDogBreedImagePresentation =
        mockk<DogBreedImageDomainToDogBreedImagePresentation>()

    @Before
    fun setUp() {
        breedName = "hound"
        subBreedName = "afgan"
        dogSubBreedParams = DogSubBreedUseCase.DogSubBreedParams(breedName, subBreedName)
        Dispatchers.setMain(testDispatcher)
        breedGalleryViewModel = BreedGalleryViewModel(
            breedImagesUseCase,
            subBreedUseCase,
            dogBreedImageDomainToDogBreedImagePresentation
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given BreedGallery data When Intent is LoadBreedImage Then viewState contains list of Image Urls to Show Gallery`() =
        runTest {

            val breedGalleryData = FakeGalleryData.getBreedGalleryWithSuccess()
            coEvery {
                breedImagesUseCase(breedName)
            } returns breedGalleryData

            breedGalleryViewModel.sendIntent(BreedGalleryViewIntent.LoadBreedImage(breedName))

            assertEquals(
                breedGalleryViewModel.viewState.value,
                breedGalleryData
            )
        }

    @Test
    fun `Given empty gallery When Intent is LoadBreedImage Then viewState is set to NoDogBreedImages`() =
        runTest {
            coEvery {
                breedImagesUseCase(breedName)
            } returns FakeGalleryData.getEmptyBreedGallery()
            breedGalleryViewModel.sendIntent(BreedGalleryViewIntent.LoadBreedImage(breedName))
            assertEquals(
                breedGalleryViewModel.viewState.value,
                DogBreedImagesState.NoDogBreedImages
            )
        }

    @Test
    fun `Given data Error When Intent is LoadBreedImage Then viewState is set to Data Error with Error message`() =
        runTest {
            coEvery {
                breedImagesUseCase(breedName)
            } returns FakeGalleryData.getException()
            breedGalleryViewModel.sendIntent(BreedGalleryViewIntent.LoadBreedImage(breedName))
            assertEquals(
                breedGalleryViewModel.viewState.value,
                DogBreedImagesState.Error(errorMsg)
            )
        }


    @Test
    fun `Given BreedGallery data When Intent is LoadSubBreedImage Then viewState contains list of Image Urls to Show Gallery`() =
        runTest {
            coEvery {
                subBreedUseCase(dogSubBreedParams)
            } returns FakeGalleryData.getBreedGalleryWithSuccess()
            breedGalleryViewModel.sendIntent(
                BreedGalleryViewIntent.LoadSubBreedImage(
                    breedName,
                    subBreedName
                )
            )
            assertEquals(
                breedGalleryViewModel.viewState.value,
                DogBreedImagesState.DogBreedImages(
                    listOf(
                        DogBreedImagePresentation("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg")
                    )
                )
            )
        }

    @Test
    fun `Given empty data When Intent is LoadSubBreedImage Then viewState is set to No Dog Images`() =
        runTest {
            coEvery {
                subBreedUseCase(dogSubBreedParams)
            } returns FakeGalleryData.getEmptyBreedGallery()
            breedGalleryViewModel.sendIntent(
                BreedGalleryViewIntent.LoadSubBreedImage(
                    breedName,
                    subBreedName
                )
            )
            assertEquals(
                breedGalleryViewModel.viewState.value,
                DogBreedImagesState.NoDogBreedImages
            )
        }

    @Test
    fun `Given data error When Intent is LoadSubBreedImage Then viewState is set to data error with error message`() =
        runTest {
            coEvery {
                subBreedUseCase(dogSubBreedParams)
            } returns FakeGalleryData.getException()
            breedGalleryViewModel.sendIntent(
                BreedGalleryViewIntent.LoadSubBreedImage(
                    breedName,
                    subBreedName
                )
            )
            assertEquals(
                breedGalleryViewModel.viewState.value,
                DogBreedImagesState.Error(errorMsg)
            )
        }

}