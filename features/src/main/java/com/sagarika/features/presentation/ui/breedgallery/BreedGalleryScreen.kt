package com.sagarika.features.presentation.ui.breedgallery

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadImage
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator

@Composable
fun BreedGalleryScreen(
) {
    val breedGalleryViewModel: BreedGalleryViewModel = hiltViewModel()


    when (val viewState = breedGalleryViewModel.viewState.collectAsState().value) {
        is BreedGalleryMVIContract.BreedGalleryViewState.Loading -> {
            LoadingIndicator()
        }

        is BreedGalleryMVIContract.BreedGalleryViewState.Error -> {
            ErrorViewInABox()
        }

        is BreedGalleryMVIContract.BreedGalleryViewState.NoDogBreedImages -> {
            PhotoGrid(emptyList())
        }

        is BreedGalleryMVIContract.BreedGalleryViewState.DogBreedImages -> {
            PhotoGrid(viewState.dogBreedImages)
        }
    }

}

@Composable
fun PhotoGrid(dogBreedImageList: List<DogBreedImagePresentation>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(dogBreedImageList.size) {
            Card(
                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(10.dp),
            ) {

                LoadImage(
                    url = dogBreedImageList[it].imageUrl,
                    description = "photo",
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}

