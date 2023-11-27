package com.sagarika.features.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sagarika.features.presentation.constants.FontSize
import com.sagarika.features.presentation.constants.back
import com.sagarika.features.presentation.ui.customcomposables.CustomText

@Composable
fun BaseScreen(
    title: String,
    showBackButton: Boolean,
    onBackClicked: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MyAppBar(title)
        content()
    }

}

@Composable
fun MyAppBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClicked: (() -> Unit)? = null
) {

    TopAppBar(
        elevation = 4.dp,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showBackButton) {
                    IconButton(onClick = {
                        onBackClicked?.invoke()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = back)
                    }
                }

                CustomText(
                    text = title,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(1f)
                    )
            }
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
    )
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview(){
MaterialTheme{
    BaseScreen(title = "The Dog Show", showBackButton = true){
        Text("Hello")
    }
}
}