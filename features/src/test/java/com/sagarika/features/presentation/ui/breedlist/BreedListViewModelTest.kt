package com.sagarika.features.presentation.ui.breedlist

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.DogBreedDomainToDogBreedPresentation
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import com.sagarika.features.presentation.ui.breedlist.fakes.FakeData
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

class BreedListViewModelTest {
    private var getDogBreedsUseCase = mockk<BreedListUseCase>()
    private lateinit var dogBreedViewModel: BreedListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    private val dogBreedDomainToDogBreedPresentation = mockk<DogBreedDomainToDogBreedPresentation>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dogBreedViewModel =
            BreedListViewModel(getDogBreedsUseCase, dogBreedDomainToDogBreedPresentation)
    }

    @Test
    fun `GIVEN breed list data WHEN LoadData ViewIntent sent THEN viewState contains list of breeds`() =
        runTest {
            val firstBreed = FakeData.getBreedListWithSuccess()[0]
            coEvery {
                getDogBreedsUseCase()
            } returns Result.Success(FakeData.getBreedListWithSuccess())

            coEvery { dogBreedDomainToDogBreedPresentation.map(firstBreed) } returns FakeData.getMappedBreedHound()
            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                FakeData.getMappedBreedHound()
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
    fun `GIVEN error WHEN LoadData ViewIntent sent THEN viewState is set to Data Error State`() =
        runTest {


            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getException()
            dogBreedViewModel.sendIntent(BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListViewState.Error(errorMessage = errorMsg)
            )
        }


    @After
    fun clean() {
        Dispatchers.resetMain()
    }

}
