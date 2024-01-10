package com.sagarika.features.presentation.ui.breedlist

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.breedList
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import com.sagarika.features.presentation.ui.breedlist.fakes.FakeData
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedListScreenSnapShotTest {

    private val testDispatcher = StandardTestDispatcher()
    private var getDogBreedsUseCase = mockk<BreedListUseCase>()
    private lateinit var dogBreedViewModel: BreedListViewModel

    @get: Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_2)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dogBreedViewModel =
            BreedListViewModel(getDogBreedsUseCase)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun createbreedListRow() {
        paparazzi.snapshot("breed_list_item") {
            BreedListRow(
                dogBreedPresentation = FakeData.dogBreedPresentation,
                breedListViewModel = dogBreedViewModel
            )
        }
    }

    @Test
    fun createsubbreedListRow() {
        paparazzi.snapshot("subbreed_list_item") {
            SubBreedListRow(
                subBreed = FakeData.subBreedPresentation, breedListViewModel = dogBreedViewModel
            )
        }
    }
}