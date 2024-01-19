package com.sagarika.features.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        MyAppBar(title, showBackButton, onBackClicked)
        content()
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(title: String,  showBackButton: Boolean = false,  onBackClicked: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
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
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }
        },

    )
}
