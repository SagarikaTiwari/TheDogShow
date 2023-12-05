package com.sagarika.features.presentation.ui.breedgallery

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sagarika.common.Response
import com.sagarika.features.presentation.model.BreedImage
import com.sagarika.features.presentation.ui.breedgallery.BreedGalleryViewModel
import com.sagarika.features.presentation.ui.breedlist.BreedListRow
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadImage
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator

@Composable
fun BreedGalleryScreen(
    breedName: String,
    breedGalleryViewModel: BreedGalleryViewModel,
) {
    LaunchedEffect(true) {
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
            PhotoGrid(viewState.data)
        }
    }

}

@Composable
fun PhotoGrid(breedImage: BreedImage) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(breedImage.message.size) {
            Card(
                border = BorderStroke(2.dp, color = MaterialTheme.colors.background),
                elevation = 15.dp,
            ) {

                LoadImage(
                    url = breedImage.message[it],
                    description = "photo",
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}

