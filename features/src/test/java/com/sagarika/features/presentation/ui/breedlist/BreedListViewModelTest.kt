package com.sagarika.features.presentation.ui.breedlist

import android.os.Build
import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.constants.servererrorMsg
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import com.sagarika.features.presentation.ui.breedlist.fakes.FakeData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)

class BreedListViewModelTest {
    private var getDogBreedsUseCase = mockk<BreedListUseCase>()
    private lateinit var dogBreedViewModel: BreedListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dogBreedViewModel =
            BreedListViewModel(getDogBreedsUseCase)
    }
    @Test
    fun `GIVEN breed list data WHEN LoadData ViewIntent sent THEN viewState contains list of breeds`() =
        runTest {
            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getBreedListWithSuccess()

            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListViewState.DogBreeds(
                    dogBreeds = listOf(
                        DogBreedPresentation(
                            breedNameInitial = "H",
                            breedName = "Hound",
                            subBreeds = listOf(DogSubBreedPresentation("A", "Hound", "Afd"))
                        ),
                        DogBreedPresentation(
                            breedNameInitial = "A",
                            breedName = "African",
                            subBreeds = emptyList()
                        )
                    )
                )
            )
        }


    @Test
    fun `GIVEN empty data WHEN LoadData ViewIntent sent THEN viewState is set to No Breeds`() =
        runTest {
            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getBreedListWithNoBreeds()

            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListViewState.NoDogBreeds
            )
        }

    @Test
    fun `GIVEN data error WHEN LoadData ViewIntent sent THEN viewState is set to Data Error State`() =
        runTest {
            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getBreedListWithDataError()

            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListViewState.Error(errorMessage = errorMsg)
            )
        }

    @Test
    fun `GIVEN Server error WHEN LoadData ViewIntent sent THEN viewState is set to Network Error State`() =
        runTest {
            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getBreedListWithServerError()

            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListViewState.Error(errorMessage = servererrorMsg)
            )
        }

    @After
    fun clean() {
        Dispatchers.resetMain()
    }

}
