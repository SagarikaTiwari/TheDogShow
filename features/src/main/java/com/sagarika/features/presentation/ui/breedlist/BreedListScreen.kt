package com.sagarika.features.presentation.ui.breedlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sagarika.features.R
import com.sagarika.features.presentation.model.Message
import com.sagarika.features.presentation.ui.customcomposables.CustomText
import com.sagarika.features.presentation.ui.customcomposables.ErrorViewInABox
import com.sagarika.features.presentation.ui.customcomposables.LoadingIndicator

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
                callback(it.breedName)
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

        is BreedListViewState.Success -> {
            val breedList = (breedListState as BreedListViewState.Success)
            var breedListArray = stringArrayResource(R.array.breed_list)

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                items(breedListArray.size) {
                    Card(
                        border = BorderStroke(2.dp, color = MaterialTheme.colors.background),
                        elevation = 15.dp,
                    ) {
                        BreedListRow(
                            breedName = breedListArray[it],
                            message = breedList.data.message,
                            breedListViewModel = breedListViewModel
                        )
                    }
                }
            }
        }

        else -> {}

    }
}

@Composable
fun BreedListRow(breedName: String, message: Message, breedListViewModel: BreedListViewModel ) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    breedListViewModel.sendIntent(BreedListViewIntent.OnBreedClick(breedName))
                 }
        ) {
            CustomText(
                breedName,
                Modifier
                    .fillMaxWidth(1f)
                    .padding(10.dp),
                color = MaterialTheme.colors.primary
            )
        }

    }


}