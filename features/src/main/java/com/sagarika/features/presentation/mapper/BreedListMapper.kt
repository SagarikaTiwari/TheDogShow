package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.BreedsModel
import com.sagarika.features.presentation.model.Breeds
import javax.inject.Inject


class BreedListMapper @Inject constructor(
    private val messageMapper: MessageMapper
) {
    fun mapBreedsModelToBreeds(breedsModel: BreedsModel): Breeds {
        with(breedsModel) {
            return Breeds(messageMapper.mapMessageModelToMessage(messageModel), status)
        }
    }
}