package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.DogBreedImage
import com.sagarika.features.presentation.model.DogBreedImagePresentation
import org.junit.Before
import org.junit.Test

class DogBreedImageDomainToDogBreedImagePresentationTest {
    private lateinit var mapper : DogBreedImageDomainToDogBreedImagePresentation

    @Before
    fun setUp(){
        mapper = DogBreedImageDomainToDogBreedImagePresentation()
    }
    @Test
    fun `Given dogBreedimage  when DogBreedImageDomainToDogBreedImagePresentation mapper is called Then it returns dogBreedImagepresentation`() {
        val dogBreedImage = DogBreedImage(
            imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        )
        val expected = DogBreedImagePresentation(
            imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
        )
        assert(mapper.map(dogBreedImage)== expected)
    }

}