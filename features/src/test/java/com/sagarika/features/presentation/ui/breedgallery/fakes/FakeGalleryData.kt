package com.sagarika.features.presentation.ui.breedgallery.fakes

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.model.DogBreedImagePresentation

object FakeGalleryData {

     fun getBreedGalleryWithSuccess(): List<DogBreedImage> =

            listOf(
                DogBreedImage(
                    imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
                )
            )
    fun getMappedBreedGalleryData(): List<DogBreedImagePresentation> =
        listOf(
            DogBreedImagePresentation(
                imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
            )
        )

    fun getEmptyBreedGallery(): Result<List<DogBreedImage>> =
        Result.Success(emptyList<DogBreedImage>())
    fun getException(): Result.Error {
        val exception = Exception(errorMsg)
        return Result.Error(exception)
    }

}