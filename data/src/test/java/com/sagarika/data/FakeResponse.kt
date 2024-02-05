package com.sagarika.data

import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.model.DogSubBreed

object FakeResponse {

    fun getAllBreed() = DogBreedResponse(
        status = "success",
        message =
        mapOf(
            "hound" to listOf("afghan", "basset"),
            "australian" to listOf("shepherd")
        )
    )

    fun getAllBreedsMapped() = listOf(
        DogBreed("hound", listOf(DogSubBreed("afghan"), DogSubBreed("basset"))),
        DogBreed("australian", listOf(DogSubBreed("shepherd")))
    )


    fun getBreedImages() = DogBreedImageResponse(
        status = "success",
        message =
        listOf(
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
            "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
        )
    )

    fun getBreedImagesMapped() = listOf<DogBreedImage>(
        DogBreedImage("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"),
        DogBreedImage("https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg")
    )
}