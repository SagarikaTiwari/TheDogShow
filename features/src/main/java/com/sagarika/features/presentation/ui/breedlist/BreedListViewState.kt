package com.sagarika.features.presentation.ui.breedlist

import androidx.annotation.StringRes
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState


sealed interface BreedListViewState: ViewState {
    object Loading : BreedListViewState
    data class Error( val errorMessage: String) : BreedListViewState
    data class DogBreeds(val dogBreeds: List<DogBreedPresentation>) : BreedListViewState
    object NoDogBreeds : BreedListViewState
}

sealed interface BreedListViewIntent: ViewIntent {
    object LoadData: BreedListViewIntent

    class OnBreedClick(val breedName: String): BreedListViewIntent

    class OnSubBreedClick(val breedName: String, val subBreedName : String): BreedListViewIntent

}

sealed interface BreedListSideEffect: SideEffect {
    class ShowGallery(val breedName: String) : BreedListSideEffect
    class ShowSubBreedGallery(val breedName: String, val subBreed : String) : BreedListSideEffect

}