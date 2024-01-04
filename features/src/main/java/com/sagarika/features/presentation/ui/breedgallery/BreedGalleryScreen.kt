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
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadImage
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator

@Composable
fun BreedGalleryScreen(
    breedName: String,
    breedGalleryViewModel: BreedGalleryViewModel,
) {

    val breedSubbreedList = breedName.split("$")

    LaunchedEffect(true) {
        if (breedSubbreedList[1].length > 1) {
            breedGalleryViewModel.sendIntent(
                BreedGalleryViewIntent.LoadSubBreedImage(
                    breedSubbreedList[0],
                    breedSubbreedList[1]
                )
            )

        } else {
            breedGalleryViewModel.sendIntent(BreedGalleryViewIntent.LoadBreedImage(breedSubbreedList[0]))

        }
    }

    when (val viewState = breedGalleryViewModel.dogBreedImagesState.collectAsState().value) {
        is DogBreedImagesState.Loading -> {
            LoadingIndicator()
        }

        is DogBreedImagesState.Error -> {
            ErrorViewInABox()
        }

        is DogBreedImagesState.DogBreedImages -> {
            PhotoGrid(viewState.dogBreedImages)
        }

        else -> {}
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

