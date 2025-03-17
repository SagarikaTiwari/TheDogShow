package com.sagarika.features.presentation.ui.base

import com.sagarika.features.presentation.ui.breedgallery.BreedGalleryMVIContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MVIDelegate internal constructor( initialState: ViewState) :
    MVI<ViewState, ViewIntent, SideEffect> {
    private val _viewState = MutableStateFlow<ViewState>(initialState)
    override val viewState = _viewState.asStateFlow()


    private val _sideEffect = MutableSharedFlow<SideEffect>()
    override val sideEffect = _sideEffect.asSharedFlow()
    override fun sendIntent(intent: ViewIntent) {
     }

    override fun CoroutineScope.emitSideEffect(effect: SideEffect) {
        this.launch {
            _sideEffect.emit(effect)
        }
    }

    override suspend fun updateViewState(viewState: ViewState) {
        _viewState.emit(viewState)
     }

}

fun <ViewState, ViewIntent, SideEffect> mvi(
    initialUiState: com.sagarika.features.presentation.ui.base.ViewState
): MVIDelegate = MVIDelegate(initialUiState)