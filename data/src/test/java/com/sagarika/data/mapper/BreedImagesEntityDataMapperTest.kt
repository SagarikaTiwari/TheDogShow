package com.sagarika.data.mapper

import com.sagarika.data.dto.BreedImagesDTO
import com.sagarika.domain.model.BreedImagesModel
import org.junit.Assert.*
import org.junit.Test

class BreedImagesEntityDataMapperTest {
    companion object {
        lateinit var breedImagesEntityDataMapper: BreedImagesEntityDataMapper
        val breedImagesDTO = BreedImagesDTO(
            listOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
            ), "success"
        )
        val breedImagesModel = BreedImagesModel(
            listOf(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
            ), "success"
        )
    }

    @Test
    fun `when mapper function is called then it maps breedImagesDto to breedImagesModel`() {
        breedImagesEntityDataMapper = BreedImagesEntityDataMapper()
        val breedimagesResponse = breedImagesEntityDataMapper.mapBreedImagesEntityToBreedImages(
            breedImagesDTO
        )
        assert(breedimagesResponse == breedImagesModel)
    }
}