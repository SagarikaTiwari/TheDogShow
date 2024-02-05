package com.sagarika.data.mapper

import com.sagarika.data.FakeResponse
import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.domain.model.DogBreedImage
import org.junit.Before
import org.junit.Test

class DogBreedImageResponseToDogBreedImageDTOKtTest {
    private lateinit var mapper: DogBreedImageResponseToDogBreedImageDTO

    @Before
    fun setUp() {
        mapper = DogBreedImageResponseToDogBreedImageDTO()
    }

    @Test
    fun `dogBreedImagesResponse to domain should return empty list`() {
        val dogBreedImageResponse = DogBreedImageResponse(
            status = "success",
            message =
            listOf()
        )

        assert(mapper.map(dogBreedImageResponse).isEmpty())
    }

    @Test
    fun `dogBreedImagesResponse to domain should return list`() {
        val fakeResponse = FakeResponse.getBreedImages()
        val expected = listOf(
            DogBreedImage(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
            ),
            DogBreedImage(
                "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg"
            ),
        )

        val result = mapper.map(fakeResponse)
        assert(result.isNotEmpty())
        assert(result == expected)
    }
}