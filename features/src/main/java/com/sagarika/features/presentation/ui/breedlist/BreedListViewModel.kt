package com.sagarika.features.presentation.ui.breedlist

import androidx.lifecycle.viewModelScope
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
import com.sagarika.features.presentation.mapper.DogBreedDomainToDogBreedPresentation
 import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.ui.breedgallery.DogBreedImagesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getDogBreeds: BreedListUseCase,
    private val dogBreedDomainToDogBreedPresentation: DogBreedDomainToDogBreedPresentation
) :
    BaseViewModel<BreedListViewState, BreedListViewIntent, BreedListSideEffect>() {

    init {
        sendIntent(BreedListViewIntent.LoadData)
    }

    private fun showBreedGallery(breedName: String) {
        viewModelScope.launch {
            _sideEffect.emit(BreedListSideEffect.ShowGallery(breedName))
        }
    }

    private fun showSubBreedGallery(breedName: String, subBreed: String) {
        viewModelScope.launch {
            _sideEffect.emit(BreedListSideEffect.ShowSubBreedGallery(breedName, subBreed))
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
            when (val result = getDogBreeds()) {
                is Result.Success -> {
                    result.data?.let { handleSuccess(it) }
                }

                is Result.Error -> {
                    _viewState.emit(BreedListViewState.Error(errorMsg))
                }
            }
        }
    }


    private suspend fun handleSuccess(dogBreeds: List<DogBreed>) {
        if (dogBreeds.isEmpty()) {
            _viewState.emit(BreedListViewState.NoDogBreeds)
        } else {
            _viewState.emit(BreedListViewState.DogBreeds(dogBreeds.map {
                dogBreedDomainToDogBreedPresentation.map(it)
            }))
        }
    }
    override fun createInitialState() = BreedListViewState.Loading
}
