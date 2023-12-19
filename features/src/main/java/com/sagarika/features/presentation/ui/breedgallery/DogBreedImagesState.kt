package com.sagarika.features.presentation.ui.breedgallery

import androidx.annotation.StringRes
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState

sealed class DogBreedImagesState : ViewState{
    object Loading : DogBreedImagesState()
    data class Error( val errorMessageId: String) : DogBreedImagesState()
    data class DogBreedImages(val dogBreedImages: List<DogBreedImagePresentation>) :
        DogBreedImagesState()

    object NoDogBreedImages : DogBreedImagesState()
}


sealed interface BreedGalleryViewIntent : ViewIntent {
    class LoadBreedImage(val breedName : String) : BreedGalleryViewIntent
    class LoadSubBreedImage(val breedName : String,val subbreedName : String) : BreedGalleryViewIntent

    object OnBackPressed : BreedGalleryViewIntent
}

sealed interface BreedGallerySideEffect : SideEffect {
}
