package com.sagarika.features.presentation.ui.customcomposables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter

@Composable
fun LoadImage(url: String, description: String, modifier: Modifier) {

    val painter = rememberAsyncImagePainter(
        url
    )
    Image(
        painter = painter,
        contentDescription = description,
        modifier = modifier

    )
}