package com.sagarika.features.presentation.ui.breedgallery

import androidx.lifecycle.viewModelScope
import com.sagarika.common.Response
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.BreedGalleryMapper
import com.sagarika.features.presentation.mapper.BreedListMapper
import com.sagarika.features.presentation.ui.base.BaseViewModel
import com.sagarika.features.presentation.ui.breedlist.BreedListSideEffect
import com.sagarika.features.presentation.ui.breedlist.BreedListViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedGalleryViewModel @Inject constructor(
    private val breedImagesUseCase: BreedImagesUseCase,
    private val breedGalleryMapper: BreedGalleryMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<BreedGalleryViewState, BreedGalleryViewIntent, BreedGallerySideEffect>() {

    private val _viewState =
        MutableStateFlow<BreedGalleryViewState>(BreedGalleryViewState.Loading)
    val viewState: StateFlow<BreedGalleryViewState> = _viewState
    var isLoading = false

    override fun sendIntent(intent: BreedGalleryViewIntent) {
        when (intent) {
            is BreedGalleryViewIntent.LoadData -> fetchBreedImages(breedName = intent.breedName)
            is BreedGalleryViewIntent.OnBackPressed -> goToMainScreen()
        }
    }

    private fun fetchBreedImages(breedName: String) {
        viewModelScope.launch(dispatcher) {
            isLoading = true
            when (val breedGallery = breedImagesUseCase(breedName = breedName)) {
                is Response.Error -> {
                    _viewState.value = BreedGalleryViewState.Error(errorMsg = errorMsg)
                    isLoading = false
                }

                is Response.Success -> {
                    _viewState.value =
                        BreedGalleryViewState.Success(
                            breedGalleryMapper.mapBreedImageModelToBreedImage(breedGallery.data)
                        )
                    isLoading = false
                }
            }
        }
    }

    private fun goToMainScreen() {

    }
}
