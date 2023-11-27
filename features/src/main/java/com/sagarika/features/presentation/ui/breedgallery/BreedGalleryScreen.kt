package com.sagarika.features.presentation.ui.breedgallery

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.sagarika.common.Response
import com.sagarika.features.presentation.model.BreedImage
import com.sagarika.features.presentation.ui.breedgallery.BreedGalleryViewModel
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator

@Composable
fun BreedGalleryScreen(
    breedName: String,
    breedGalleryViewModel: BreedGalleryViewModel,
) {
    LaunchedEffect(1) {
        breedGalleryViewModel.sendIntent(BreedGalleryViewIntent.LoadData(breedName))
    }
    val viewState = breedGalleryViewModel.viewState.collectAsState().value

    when (viewState) {
        is BreedGalleryViewState.Loading -> {
            LoadingIndicator()
        }

        is BreedGalleryViewState.Error -> {
            ErrorViewInABox()
        }

        is BreedGalleryViewState.Success -> {
            BreedGalleryGrid(viewState.data)
        }
    }

}


@Composable
fun BreedGalleryGrid(breedImage: BreedImage) {

}