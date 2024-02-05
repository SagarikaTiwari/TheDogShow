package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import javax.inject.Inject

class DogBreedImageDomainToDogBreedImagePresentation @Inject constructor() {
    fun map(dogBreedImage: DogBreedImage) = DogBreedImagePresentation(dogBreedImage.imageUrl)
}