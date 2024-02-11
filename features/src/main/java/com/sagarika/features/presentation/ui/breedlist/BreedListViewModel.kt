package com.sagarika.features.presentation.ui.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.DogBreedDomainToDogBreedPresentation
import com.sagarika.features.presentation.ui.base.MVI
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState
import com.sagarika.features.presentation.ui.base.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getDogBreeds: BreedListUseCase,
    private val dogBreedDomainToDogBreedPresentation: DogBreedDomainToDogBreedPresentation
) :
    ViewModel(),
    MVI<ViewState, ViewIntent, SideEffect> by mvi<BreedListMVIContract.BreedListViewState, BreedListMVIContract.BreedListViewIntent, BreedListMVIContract.BreedListSideEffect>(
        BreedListMVIContract.BreedListViewState.Loading
    ) {

    init {
        sendIntent(BreedListMVIContract.BreedListViewIntent.LoadData)
    }

    private fun showBreedGallery(breedName: String) {
        viewModelScope.launch {
            emitSideEffect(BreedListMVIContract.BreedListSideEffect.ShowGallery(breedName))
        }
    }

    private fun showSubBreedGallery(breedName: String, subBreed: String) {
        viewModelScope.launch {
            emitSideEffect(BreedListMVIContract.BreedListSideEffect.ShowSubBreedGallery(breedName, subBreed))
        }
    }

     fun sendIntent(intent: BreedListMVIContract.BreedListViewIntent) {
        when (intent) {
            is BreedListMVIContract.BreedListViewIntent.LoadData -> fetchBreedList()
            is BreedListMVIContract.BreedListViewIntent.OnBreedClick -> showBreedGallery(intent.breedName)
            is BreedListMVIContract.BreedListViewIntent.OnSubBreedClick -> showSubBreedGallery(
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
                    updateViewState(BreedListMVIContract.BreedListViewState.Error(errorMsg))
                }
            }
        }
    }


    private fun handleSuccess(dogBreeds: List<DogBreed>) {
        if (dogBreeds.isEmpty()) {
            updateViewState(BreedListMVIContract.BreedListViewState.NoDogBreeds)
        } else {
            updateViewState(BreedListMVIContract.BreedListViewState.DogBreeds(dogBreeds.map {
                dogBreedDomainToDogBreedPresentation.map(it)
            }))
        }
    }

 }
