package com.sagarika.features.presentation.ui.customcomposables


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sagarika.features.R
import com.sagarika.features.presentation.constants.errorMsg

@Composable

fun LoadingIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colors.primary,
    )
}

@Composable
fun ErrorViewInABox() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
    ) {
        Text(
            text = errorMsg,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MyTextView(
    modifier: Modifier,
    text: String,
    color: Color,
    fontSize: TextUnit,
    fontFamily: androidx.compose.ui.text.font.FontFamily,
    textAlign: TextAlign
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamily,
        textAlign = textAlign
    )
}



