package com.sagarika.features.presentation.ui.breedgallery.fakes

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.constants.errorMsg

object FakeGalleryData {

     fun getBreedGalleryWithSuccess(): Result<List<DogBreedImage>> =
        Result.Success(
            listOf(
                DogBreedImage(
                    imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
                )
            )
        )

    fun getEmptyBreedGallery(): Result<List<DogBreedImage>> =
        Result.Success(emptyList<DogBreedImage>())
    fun getException(): Result.Error {
        val exception = Exception(errorMsg)
        return Result.Error(exception)
    }

}