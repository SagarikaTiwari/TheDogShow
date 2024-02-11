package com.sagarika.features.presentation.ui.breedgallery

import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.base.MVI
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState


interface BreedGalleryMVIContract : MVI<ViewState, ViewIntent, SideEffect> {
    sealed interface BreedGalleryViewState : ViewState {
        data object Loading : BreedGalleryViewState
        data class Error(val errorMessageId: String) : BreedGalleryViewState
        data class DogBreedImages(val dogBreedImages: List<DogBreedImagePresentation>) :
            BreedGalleryViewState

        data object NoDogBreedImages : BreedGalleryViewState
    }


    sealed interface BreedGalleryViewIntent : ViewIntent {
        class LoadBreedImage(val breedName: String) : BreedGalleryViewIntent
        class LoadSubBreedImage(val breedName: String, val subbreedName: String) :
            BreedGalleryViewIntent

        data object OnBackPressed : BreedGalleryViewIntent
    }

    sealed interface BreedGallerySideEffect : SideEffect {
    }
}