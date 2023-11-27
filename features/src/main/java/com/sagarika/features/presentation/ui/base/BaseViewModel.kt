package com.sagarika.features.presentation.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<VS: ViewState, VI: ViewIntent, SE: SideEffect>: ViewModel() {
    abstract fun sendIntent(intent: VI)

}
