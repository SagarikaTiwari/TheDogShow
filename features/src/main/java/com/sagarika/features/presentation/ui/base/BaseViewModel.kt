package com.sagarika.features.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.sagarika.features.presentation.ui.breedlist.BreedListSideEffect
import com.sagarika.features.presentation.ui.breedlist.BreedListViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect> : ViewModel() {

    private val initialState: VS by lazy { createInitialState() }

    protected val _viewState = MutableStateFlow(initialState)

    val viewState: StateFlow<VS>
        get() = _viewState

    protected val _sideEffect = MutableSharedFlow<SE>()

    val sideEffect: SharedFlow<SE> get() = _sideEffect


    abstract fun createInitialState(): VS

    abstract fun sendIntent(intent: VI)

}
