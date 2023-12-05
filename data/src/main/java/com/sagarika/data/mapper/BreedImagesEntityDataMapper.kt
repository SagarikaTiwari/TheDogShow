package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedImagesDTO
import com.sagarika.domain.model.BreedImagesModel
import javax.inject.Inject

class BreedImagesEntityDataMapper  @Inject constructor(){

    fun mapBreedImagesEntityToBreedImages(breedImagesDTO: BreedImagesDTO): BreedImagesModel {
        with(breedImagesDTO) {
            return BreedImagesModel(message, status)
        }
    }
}