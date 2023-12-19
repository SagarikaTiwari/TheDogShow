package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.model.DogBreedImagePresentation

fun DogBreedImage.toPresentation() = DogBreedImagePresentation(this.imageUrl)
