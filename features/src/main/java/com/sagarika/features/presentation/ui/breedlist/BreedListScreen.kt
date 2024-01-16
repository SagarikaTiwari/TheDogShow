package com.sagarika.features.presentation.ui.breedlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import com.sagarika.features.presentation.ui.customcomposables.CustomText
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator
import com.sagarika.thedogshow.R

@Composable
fun BreedListScreen(
    breedListViewModel: BreedListViewModel,
    callback: (breedName: String) -> Unit
) {
    BreedList(breedListViewModel, callback)
}

@Composable
fun BreedList(breedListViewModel: BreedListViewModel, callback: (breedName: String) -> Unit) {
    LaunchedEffect(key1 = true, block = {
        breedListViewModel.sendIntent(
            BreedListViewIntent.LoadData
        )
        breedListViewModel.sideEffect.collect {
            if (it is BreedListSideEffect.ShowGallery) {
                callback(it.breedName.lowercase() + "$")
            }
            if (it is BreedListSideEffect.ShowSubBreedGallery) {
                callback(it.breedName.lowercase() + "$" + it.subBreed.lowercase())
            }
        }
    })
    val breedListState: BreedListViewState by breedListViewModel.viewState.collectAsState()
    when (breedListState) {

        is BreedListViewState.Loading ->
            LoadingIndicator()

        is BreedListViewState.Error -> {
            ErrorViewInABox()
        }

        is BreedListViewState.NoDogBreeds -> {
            CustomText(
                com.sagarika.features.presentation.constants.emptyListMsg,
                Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        is BreedListViewState.DogBreeds -> {
            val breedList = (breedListState as BreedListViewState.DogBreeds).dogBreeds
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                items(breedList.size) {
                    Card(
                        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.background),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Column {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                BreedListRow(
                                    dogBreedPresentation = breedList[it],
                                    breedListViewModel = breedListViewModel
                                )

                            }

                        }

                    }
                }
            }
        }


    }
}

@Composable
fun SubBreedListRow(subBreed: DogSubBreedPresentation, breedListViewModel: BreedListViewModel) {
    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    breedListViewModel.sendIntent(
                        BreedListViewIntent.OnSubBreedClick(
                            subBreed.parentBreedName, subBreed.breedName
                        )
                    )
                }
        ) {
            CustomText(
                subBreed.breedName,
                Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.primary
            )


        }


    }
}


@Composable
fun BreedListRow(
    dogBreedPresentation: DogBreedPresentation,
    breedListViewModel: BreedListViewModel
) {
    Column {
        var mExpanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            CustomText(
                dogBreedPresentation.breedName,
                Modifier
                    .width(300.dp)
                    .padding(10.dp)
                    .clickable {
                        breedListViewModel.sendIntent(
                            BreedListViewIntent.OnBreedClick(
                                dogBreedPresentation.breedName
                            )
                        )
                    },
                color = MaterialTheme.colorScheme.primary
            )
            if (dogBreedPresentation.subBreeds.isNotEmpty()) {
                val painter: Painter = if (mExpanded)
                    painterResource(id = com.sagarika.thedogshow.R.drawable.close_dropdown)
                else
                    painterResource(id = com.sagarika.thedogshow.R.drawable.add_image)
                Icon(painter = painter, contentDescription = "dropdown image",
                    modifier = Modifier.clickable {
                        mExpanded = !mExpanded
                    })
            }

        }


        if (mExpanded) {
            dogBreedPresentation.subBreeds.forEach {
                SubBreedListRow(
                    it,
                    breedListViewModel
                )
            }

        }

    }


}
