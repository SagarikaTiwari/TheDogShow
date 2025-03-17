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
import io.mockk.junit4.MockKRule
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
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class BreedListViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)
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
            coEvery {
                getDogBreedsUseCase()
            } returns Result.Success(FakeData.getBreedListWithSuccess())

            coEvery { dogBreedDomainToDogBreedPresentation.map( FakeData.getBreedListWithSuccess()[0]) } returns FakeData.getMappedBreedList()[0]
            coEvery { dogBreedDomainToDogBreedPresentation.map( FakeData.getBreedListWithSuccess()[1]) } returns FakeData.getMappedBreedList()[1]

            dogBreedViewModel.sendIntent(BreedListMVIContract.BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListMVIContract.BreedListViewState.DogBreeds(FakeData.getMappedBreedList())
            )
        }


    @Test
    fun `GIVEN empty data WHEN LoadData ViewIntent sent THEN viewState is set to No Breeds`() =
        runTest {
            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getBreedListWithNoBreeds()

            dogBreedViewModel.sendIntent(BreedListMVIContract.BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListMVIContract.BreedListViewState.NoDogBreeds
            )
        }

    @Test
    fun `GIVEN error WHEN LoadData ViewIntent sent THEN viewState is set to Data Error State`() =
        runTest {


            coEvery {
                getDogBreedsUseCase()
            } returns
                    FakeData.getException()
            dogBreedViewModel.sendIntent(BreedListMVIContract.BreedListViewIntent.LoadData)

            assertEquals(
                dogBreedViewModel.viewState.value,
                BreedListMVIContract.BreedListViewState.Error(errorMessage = errorMsg)
            )
        }


    @After
    fun clean() {
        Dispatchers.resetMain()
    }

}
