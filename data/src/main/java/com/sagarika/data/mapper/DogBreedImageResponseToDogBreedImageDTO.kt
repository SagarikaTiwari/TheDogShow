package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.domain.model.DogBreedImage
import javax.inject.Inject


class DogBreedImageResponseToDogBreedImageDTO @Inject constructor() {
    fun map(dogBreedImageResponse: DogBreedImageResponse): List<DogBreedImage> {
        return dogBreedImageResponse.message.map {
            DogBreedImage(imageUrl = it)
        }
    }
}
