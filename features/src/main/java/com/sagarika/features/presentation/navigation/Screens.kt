package com.sagarika.features.ecommerce.presentation.navigation


sealed class Screens(val route: String) {
    object BreedListScreen: Screens("breed_list_screen")
    object BreedGalleryScreen: Screens("breed_gallery_screen")
 }