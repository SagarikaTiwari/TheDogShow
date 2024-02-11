package com.sagarika.features.presentation.ui.breedlist

import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.ui.base.MVI
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState


interface BreedListMVIContract : MVI<ViewState, ViewIntent, SideEffect> {
    sealed interface BreedListViewState : ViewState {
        data object Loading : BreedListViewState
        data class Error(val errorMessage: String) : BreedListViewState
        data class DogBreeds(val dogBreeds: List<DogBreedPresentation>) : BreedListViewState
        data object NoDogBreeds : BreedListViewState
    }

    sealed interface BreedListViewIntent : ViewIntent {
        data object LoadData : BreedListViewIntent

        class OnBreedClick(val breedName: String) : BreedListViewIntent

        class OnSubBreedClick(val breedName: String, val subBreedName: String) : BreedListViewIntent

    }

    sealed interface BreedListSideEffect : SideEffect {
        class ShowGallery(val breedName: String) : BreedListSideEffect
        class ShowSubBreedGallery(val breedName: String, val subBreed: String) : BreedListSideEffect

    }
}