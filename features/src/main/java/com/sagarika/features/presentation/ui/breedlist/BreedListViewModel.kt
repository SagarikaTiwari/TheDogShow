package com.sagarika.features.presentation.ui.breedlist

import androidx.lifecycle.viewModelScope
import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import com.sagarika.features.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.constants.servererrorMsg
import com.sagarika.features.presentation.mapper.toPresentation
import com.sagarika.features.presentation.model.DogBreedPresentation
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getDogBreeds: BreedListUseCase,
) :
    BaseViewModel<BreedListViewState, BreedListViewIntent, BreedListSideEffect>() {

    private val _viewState =
        MutableStateFlow<BreedListViewState>(BreedListViewState.Loading)
    val viewState: StateFlow<BreedListViewState> = _viewState
    private val _sideEffect = Channel<BreedListSideEffect>()
    val sideEffect: Flow<BreedListSideEffect>
        get() = _sideEffect.receiveAsFlow()


    private fun showBreedGallery(breedName: String) {
        viewModelScope.launch {
            _sideEffect.send(BreedListSideEffect.ShowGallery(breedName))
        }
    }

    private fun showSubBreedGallery(breedName: String, subBreed: String) {
        viewModelScope.launch {
            _sideEffect.send(BreedListSideEffect.ShowSubBreedGallery(breedName, subBreed))
        }
    }

    override fun sendIntent(intent: BreedListViewIntent) {
        when (intent) {
            is BreedListViewIntent.LoadData -> fetchBreedList()
            is BreedListViewIntent.OnBreedClick -> showBreedGallery(intent.breedName)
            is BreedListViewIntent.OnSubBreedClick -> showSubBreedGallery(
                intent.breedName,
                intent.subBreedName
            )

        }
    }

    private fun fetchBreedList() {
        viewModelScope.launch {
            _viewState.value = BreedListViewState.Loading
            getDogBreeds().collect() { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { handleSuccess(it) }
                    }

                    is Result.Error -> {
                        handleError(failure = result.failure)
                    }
                }

            }
        }
    }


     private fun handleError(failure: Failure) {
        when (failure) {
            is Failure.DataError -> _viewState.value = BreedListViewState.Error(errorMsg)
             is Failure.ServerError -> _viewState.value = BreedListViewState.Error(servererrorMsg)
        }
    }

    private fun handleSuccess(dogBreeds: List<DogBreed>) {
        if (dogBreeds.isEmpty()) {
            _viewState.value = BreedListViewState.NoDogBreeds
        } else {
            _viewState.value = BreedListViewState.DogBreeds(dogBreeds.map {
                it.toPresentation()
            })
        }
    }


}
