package com.sagarika.features.presentation.ui.breedgallery

import androidx.lifecycle.viewModelScope
 import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.DogSubBreedUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.constants.servererrorMsg
import com.sagarika.features.presentation.mapper.DogBreedImageDomainToDogBreedImagePresentation
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.base.BaseViewModel
import com.sagarika.features.presentation.ui.breedlist.BreedListViewState
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
    private val dogBreedImageDomainToDogBreedImagePresentation: DogBreedImageDomainToDogBreedImagePresentation
) :
    BaseViewModel<DogBreedImagesState, BreedGalleryViewIntent, BreedGallerySideEffect>() {


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
        viewModelScope.launch {

            when (val result = breedImagesUseCase(breedName)) {
                is Result.Success -> {
                    result.data?.let { handleSuccess(it) }
                }

                is Result.Error -> {
                    _viewState.emit(DogBreedImagesState.Error(errorMsg))
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
                    result.data?.let {
                        handleSuccess(it)
                    }
                }

                is Result.Error -> {
                    _viewState.emit(DogBreedImagesState.Error(errorMsg))
                }
            }

        }

    }



    private suspend fun handleSuccess(dogBreedImages: List<DogBreedImage>) {
        if (dogBreedImages.isEmpty()) {
            _viewState.emit(DogBreedImagesState.NoDogBreedImages)
        } else {
            _viewState.emit(
                DogBreedImagesState.DogBreedImages(dogBreedImages.map {
                    dogBreedImageDomainToDogBreedImagePresentation.map(it)
                })
            )
        }
    }

    override fun createInitialState() = DogBreedImagesState.Loading

}
