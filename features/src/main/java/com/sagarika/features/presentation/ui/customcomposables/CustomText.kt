package com.sagarika.features.presentation.ui.customcomposables

 import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun CustomText(text: String, modifier: Modifier, color: Color) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
    )
}