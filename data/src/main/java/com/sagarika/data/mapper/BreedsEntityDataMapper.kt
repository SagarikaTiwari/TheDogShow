package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedListDTO
import com.sagarika.domain.model.BreedsModel
import javax.inject.Inject

class BreedsEntityDataMapper @Inject constructor(
    private val messageEntityDataMapper: MessageEntityDataMapper
) {
    fun mapBreedsEntityToBreeds(breedsDTO: BreedListDTO): BreedsModel {
        with(breedsDTO) {
            return BreedsModel(messageEntityDataMapper.mapMessageEntityToMessage(message), status)
        }
    }
}
