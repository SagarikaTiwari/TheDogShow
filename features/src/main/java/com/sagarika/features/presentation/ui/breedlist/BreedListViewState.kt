package com.sagarika.features.presentation.ui.breedlist

import com.sagarika.features.presentation.model.Breeds
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState


sealed interface BreedListViewState: ViewState {
    object Loading: BreedListViewState

    class Success(val data: Breeds) : BreedListViewState

    class Error(val errorMsg: String): BreedListViewState
}

sealed interface BreedListViewIntent: ViewIntent {
    object LoadData: BreedListViewIntent

    class OnBreedClick(val breedName: String): BreedListViewIntent
}

sealed interface BreedListSideEffect: SideEffect {
    class ShowGallery(val breedName: String) : BreedListSideEffect
}