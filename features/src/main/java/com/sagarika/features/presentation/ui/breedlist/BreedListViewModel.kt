package com.sagarika.features.presentation.ui.breedlist

import androidx.lifecycle.viewModelScope
import com.sagarika.common.Response
import com.sagarika.features.presentation.constants.breedList
import com.sagarika.features.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.mapper.BreedListMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val breedListUseCase: BreedListUseCase,
    private val breedListMapper: BreedListMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    BaseViewModel<BreedListViewState, BreedListViewIntent, BreedListSideEffect>() {

    private val _viewState =
        MutableStateFlow<BreedListViewState>(BreedListViewState.Loading)
    val viewState: StateFlow<BreedListViewState> = _viewState
    private val _effect: Flow<BreedListSideEffect> = TODO()
    val effect : Flow<BreedListSideEffect> = _effect
    var isLoading = false

    private fun showBreedGallery(breedName: String) {
        viewModelScope.launch {
         //   _effect.flowOn() BreedListSideEffect.ShowGallery(breedName))
        }
    }

    override fun sendIntent(intent: BreedListViewIntent) {
        when (intent) {
            is BreedListViewIntent.LoadData -> fetchBreedList()
            is BreedListViewIntent.OnBreedClick -> showBreedGallery(intent.breedName)
        }
    }

    private fun fetchBreedList() {
        viewModelScope.launch(dispatcher) {
            isLoading = true
            when (val breedList = breedListUseCase()) {
                is Response.Error -> {
                    _viewState.value = BreedListViewState.Error(errorMsg = errorMsg)
                    isLoading = false
                }
                is Response.Success -> {
                    _viewState.value =
                        BreedListViewState.Success(
                            breedListMapper.mapBreedsModelToBreeds(breedList.data)
                        )
                    isLoading = false
                }
            }
        }
    }

}
