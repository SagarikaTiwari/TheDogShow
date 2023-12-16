package com.sagarika.features.presentation.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.sagarika.features.presentation.theme.MyApplication
import org.junit.Rule
import org.junit.Test


internal class CustomTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `GivenAppStartedWhenCustomTopAppBarWithTitleDisplayedThenValidateTheTile`() {
        composeTestRule.setContent {
            MyApplication {
                MyAppBar(
                    Title,
                    false,
                    {})
            }
        }

        composeTestRule.onNodeWithText(text = Title).assertExists()
    }

    private companion object {
        const val Title = "Breed List"
    }

}