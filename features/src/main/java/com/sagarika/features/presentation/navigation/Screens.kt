package com.sagarika.features.presentation.navigation


sealed class Screens(val route: String) {
    data object BreedListScreen: Screens("breed_list_screen")
    data object BreedGalleryScreen: Screens("breed_gallery_screen")
 }