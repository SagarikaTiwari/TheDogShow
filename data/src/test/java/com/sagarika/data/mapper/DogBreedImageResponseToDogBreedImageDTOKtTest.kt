package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.domain.model.DogBreedImage
import org.junit.Test

@Test
fun `dogBreedImagesResponse to domain should return empty list`() {
    val dogBreedImageResponse = DogBreedImageResponse(
        status = "success",
        message =
        listOf()
    )
    assert(dogBreedImageResponse.toDomain().isEmpty())
}

@Test
fun `dogBreedImagesResponse to domain should return list`() {
    val dogBreedImageResponse = DogBreedImageResponse(
        status = "success",
        message =
        listOf(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
        )
    )
    val expected = listOf(
        DogBreedImage(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        ),
        DogBreedImage(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
        ),
    )
    assert(dogBreedImageResponse.toDomain().isNotEmpty())
    assert(dogBreedImageResponse.toDomain() == expected)
}