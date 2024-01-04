package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import com.sagarika.features.presentation.model.DogBreedPresentation
import org.junit.Assert.*
import org.junit.Test

class DogBreedImageDomainToDogBreedImagePresentationTest {
    @Test
    fun `Given dogBreedimage  when DogBreedImageDomainToDogBreedImagePresentation mapper is called Then it returns dogBreedImagepresentation`() {
        val dogBreedImage = DogBreedImage(
            imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        )
        val expected = DogBreedImagePresentation(
            imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        )
        assert(dogBreedImage.toPresentation() == expected)
    }

}