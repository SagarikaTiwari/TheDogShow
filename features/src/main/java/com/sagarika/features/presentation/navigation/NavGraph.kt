package com.sagarika.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sagarika.features.presentation.constants.breedImages
import com.sagarika.features.presentation.constants.breedList
import com.sagarika.features.presentation.constants.breedName
import com.sagarika.features.presentation.ui.common.BaseScreen
import com.sagarika.features.presentation.ui.breedgallery.BreedGalleryScreen
import com.sagarika.features.presentation.ui.breedlist.BreedListScreen
import com.sagarika.features.presentation.ui.breedgallery.BreedGalleryViewModel
import com.sagarika.features.presentation.ui.breedlist.BreedListViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screens.BreedListScreen.route
    )
    {
        composable(route = Screens.BreedListScreen.route) {

            BaseScreen(title = breedList, showBackButton = false, content = {
                BreedListScreen() {
                    navController.navigate(Screens.BreedGalleryScreen.route + "/$it")
                }
            })
        }

        composable(
            Screens.BreedGalleryScreen.route + "/{breedName}",
            arguments = listOf(
                navArgument(breedName) {
                    type = NavType.StringType
                },

                )
        ) {
            val breedName = remember {
                it.arguments?.getString(breedName)
            }

            BaseScreen(
                title = breedImages,
                showBackButton = true,
                onBackClicked = { navController.popBackStack() },
                content = {
                    if (breedName != null) {
                        BreedGalleryScreen()
                    }
                })
        }
    }
}

