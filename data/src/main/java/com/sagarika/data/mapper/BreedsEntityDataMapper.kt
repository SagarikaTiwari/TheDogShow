package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedsEntity
import com.sagarika.domain.entities.Breeds
import javax.inject.Inject

class BreedsEntityDataMapper @Inject constructor(
    private val messageEntityDataMapper: MessageEntityDataMapper
) {
    fun mapBreedsEntityToBreeds(breedsEntity: BreedsEntity): Breeds {
        with(breedsEntity) {
            return Breeds(messageEntityDataMapper.mapMessageEntityToMessage(messageEntity), status)
        }
    }
}
