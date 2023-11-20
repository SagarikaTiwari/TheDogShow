package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedImagesEntity
import com.sagarika.domain.entities.BreedImages

class BreedImagesEntityDataMapper {

    fun mapBreedImagesEntityToBreedImages(breedImagesEntity: BreedImagesEntity): BreedImages {
        with(breedImagesEntity) {
            return BreedImages(message, status)
        }
    }
}