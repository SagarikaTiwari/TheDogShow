package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.domain.model.DogBreedImage


fun DogBreedImageResponse.toDomain(): List<DogBreedImage> {
    return this.message.map {
        DogBreedImage(imageUrl = it)
    }
}
