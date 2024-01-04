package com.sagarika.features.presentation.ui.common
 
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
 import org.junit.Rule
import org.junit.Test
 import org.junit.runner.RunWith



internal class CustomTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `GivenAppStartedWhenCustomTopAppBarWithTitleDisplayedThenValidateTheTile`() {
        composeTestRule.setContent {
                 MyAppBar(
                    Title,
                    false
                ) {}

        }

        composeTestRule.onNodeWithText(text = Title).assertExists()
    }

    private companion object {
        const val Title = "Breed List"
    }

}