package com.sagarika.features.presentation.ui.breedgallery

import androidx.lifecycle.viewModelScope
import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.DogSubBreedUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.toPresentation
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedGalleryViewModel @Inject constructor(
    private val breedImagesUseCase: BreedImagesUseCase,
    private val subBreedUseCase: DogSubBreedUseCase,
) :
    BaseViewModel<DogBreedImagesState, BreedGalleryViewIntent, BreedGallerySideEffect>() {

    private val _dogBreedImagesState =
        MutableStateFlow<DogBreedImagesState>(DogBreedImagesState.Loading)
    val dogBreedImagesState: StateFlow<DogBreedImagesState> = _dogBreedImagesState

    override fun sendIntent(intent: BreedGalleryViewIntent) {
        when (intent) {
            is BreedGalleryViewIntent.LoadBreedImage -> fetchBreedImages(breedName = intent.breedName)
            is BreedGalleryViewIntent.LoadSubBreedImage -> fetchDogSubBreedImages(
                intent.breedName,
                intent.subbreedName
            )

            else -> {}
        }
    }

    private fun fetchBreedImages(breedName: String) {
        _dogBreedImagesState.value = DogBreedImagesState.Loading

        viewModelScope.launch {
            breedImagesUseCase(breedName).collect() {
                when (it) {
                    is Result.Success -> {
                        it.data?.let { it1 -> handleSuccess(it1) }
                    }

                    is Result.Error -> {
                        handleError(it.failure)
                    }
                }
            }
        }

    }

    private fun fetchDogSubBreedImages(breedName: String, subBreedName: String) {

        _dogBreedImagesState.value = DogBreedImagesState.Loading
        viewModelScope.launch {
            subBreedUseCase(
                DogSubBreedUseCase.DogSubBreedParams(breedName, subBreedName)
            ).collect() {
                when (it) {
                    is Result.Success -> {
                        it.data?.let { it1 -> handleSuccess(it1) }
                    }

                    is Result.Error -> {
                        handleError(it.failure)
                    }
                }
            }
        }

    }

    @Suppress("UNUSED_PARAMETER")
    private fun handleError(failure: Failure) {
        _dogBreedImagesState.value = DogBreedImagesState.Error(errorMsg)
    }

    private fun handleSuccess(dogBreedImages: List<DogBreedImage>) {
        if (dogBreedImages.isEmpty()) {
            _dogBreedImagesState.value = DogBreedImagesState.NoDogBreedImages
        } else {
            _dogBreedImagesState.value =
                DogBreedImagesState.DogBreedImages(dogBreedImages.map {
                    it.toPresentation()
                })
        }
    }




}
