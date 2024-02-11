package com.sagarika.features.presentation.ui.breedgallery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.DogSubBreedUseCase
import com.sagarika.features.presentation.constants.breedName
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.DogBreedImageDomainToDogBreedImagePresentation
import com.sagarika.features.presentation.ui.base.MVI
import com.sagarika.features.presentation.ui.base.SideEffect
import com.sagarika.features.presentation.ui.base.ViewIntent
import com.sagarika.features.presentation.ui.base.ViewState
import com.sagarika.features.presentation.ui.base.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedGalleryViewModel @Inject constructor(
    private val breedImagesUseCase: BreedImagesUseCase,
    private val subBreedUseCase: DogSubBreedUseCase,
    private val dogBreedImageDomainToDogBreedImagePresentation: DogBreedImageDomainToDogBreedImagePresentation,
    savedStateHandle: SavedStateHandle

) : ViewModel(),
    MVI<ViewState, ViewIntent, SideEffect> by mvi<BreedGalleryMVIContract.BreedGalleryViewState, BreedGalleryMVIContract.BreedGalleryViewIntent, BreedGalleryMVIContract.BreedGallerySideEffect>(
        BreedGalleryMVIContract.BreedGalleryViewState.Loading
    ) {

    init {
        val breedName = savedStateHandle.get<String>(breedName)
        if (breedName != null) {
            val breedSubbreedList = breedName.split("$")
            if (breedSubbreedList[1].length > 1) {
                sendIntent(
                    BreedGalleryMVIContract.BreedGalleryViewIntent.LoadSubBreedImage(
                        breedSubbreedList[0],
                        breedSubbreedList[1]
                    )
                )
            } else {
                sendIntent(
                    BreedGalleryMVIContract.BreedGalleryViewIntent.LoadBreedImage(
                        breedSubbreedList[0]
                    )
                )
            }
        }
    }

    fun sendIntent(intent: BreedGalleryMVIContract.BreedGalleryViewIntent) {
        when (intent) {
            is BreedGalleryMVIContract.BreedGalleryViewIntent.LoadBreedImage -> fetchBreedImages(
                breedName = intent.breedName
            )

            is BreedGalleryMVIContract.BreedGalleryViewIntent.LoadSubBreedImage -> fetchDogSubBreedImages(
                intent.breedName,
                intent.subbreedName
            )

            else -> {}
        }
    }

    private fun fetchBreedImages(breedName: String) {
        viewModelScope.launch {

            when (val result = breedImagesUseCase(breedName)) {
                is Result.Success -> {
                    handleSuccess(result.data)
                }

                is Result.Error -> {
                    updateViewState(BreedGalleryMVIContract.BreedGalleryViewState.Error(errorMsg))
                }
            }
        }

    }

    private fun fetchDogSubBreedImages(breedName: String, subBreedName: String) {

        viewModelScope.launch {
            when (val result = subBreedUseCase(
                DogSubBreedUseCase.DogSubBreedParams(breedName, subBreedName)
            )) {
                is Result.Success -> {
                    handleSuccess(result.data)
                }

                is Result.Error -> {
                    updateViewState(BreedGalleryMVIContract.BreedGalleryViewState.Error(errorMsg))
                }
            }

        }

    }


    private fun handleSuccess(dogBreedImages: List<DogBreedImage>) {
        if (dogBreedImages.isEmpty()) {
            updateViewState(BreedGalleryMVIContract.BreedGalleryViewState.NoDogBreedImages)

        } else {
            updateViewState(
                BreedGalleryMVIContract.BreedGalleryViewState.DogBreedImages(dogBreedImages.map {
                    dogBreedImageDomainToDogBreedImagePresentation.map(it)
                })
            )
        }
    }
}
