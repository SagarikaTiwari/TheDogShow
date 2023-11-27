package com.sagarika.features.presentation.ui.breedgallery


import com.sagarika.features.presentation.model.BreedImage
import com.sagarika.features.presentation.model.Breeds
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState


sealed interface BreedGalleryViewState : ViewState {
    object Loading : BreedGalleryViewState

    class Success(val data: BreedImage) : BreedGalleryViewState

    class Error(val errorMsg: String) : BreedGalleryViewState
}

sealed interface BreedGalleryViewIntent : ViewIntent {
    class LoadData(val breedName : String) : BreedGalleryViewIntent
    object OnBackPressed : BreedGalleryViewIntent
}

sealed interface BreedGallerySideEffect : SideEffect {
}