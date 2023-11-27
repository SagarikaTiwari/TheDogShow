package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.features.presentation.model.BreedImage

class BreedGalleryMapper {
    fun mapBreedImageModelToBreedImage(breedImagesModel: BreedImagesModel): BreedImage {
        with(breedImagesModel) {
            return BreedImage(message, status)
        }
    }
}
