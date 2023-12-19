package com.sagarika.features.presentation.ui.breedlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.mapper.DogBreedDomainToDogBreedPresentation
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi

class BreedListViewModelTest {
    companion object {
        @get:Rule
        var rule: TestRule = InstantTaskExecutorRule()
        private val dispatcher = StandardTestDispatcher()
        private lateinit var viewModel: BreedListViewModel
        private val breedListUseCase = mockk<BreedListUseCase>()
        private val dogBreedDomainToDogBreedPresentation =
            mockk<DogBreedDomainToDogBreedPresentation>()
    }

    @Before
    fun setUp() {
        viewModel = BreedListViewModel(breedListUseCase, dogBreedDomainToDogBreedPresentation, dispatcher)
    }

    @Test
    fun `GIVE breed list data WHEN LoadData ViewIntent sent THEN viewState contains list of breeds`() =
        runTest {

            viewModel.sendIntent(BreedListViewIntent.LoadData)
            dispatcher.scheduler.advanceUntilIdle()
            assertEquals(
                viewModel.viewState.value,
                BreedListViewState.Loading

            )
        }
}