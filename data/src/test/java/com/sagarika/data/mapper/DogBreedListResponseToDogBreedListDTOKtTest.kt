package com.sagarika.data.mapper

import com.sagarika.data.FakeResponse
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DogBreedListResponseToDogBreedListDTOKtTest {

    private lateinit var mapper: DogBreedListResponseToDogBreedListDTO

    @Before
    fun setUp() {
        mapper = DogBreedListResponseToDogBreedListDTO()
    }

    @Test
    fun `dogBreedResponse to domain should return empty list`() {
        val dogBreedResponse = DogBreedResponse(
            status = "success",
            message =
            mapOf()
        )
        assert(mapper.map(dogBreedResponse).isEmpty())
    }

    @Test
    fun `dogBreedResponse to domain should return list`() {
        val fakeResponse = FakeResponse.getAllBreed()
        val expected = listOf(
            DogBreed(
                "hound",
                subBreed = listOf(DogSubBreed("afghan"), DogSubBreed("basset"))
            ),
            DogBreed(
                "australian",
                subBreed = listOf(DogSubBreed("shepherd"))
            )
        )
        val result = mapper.map(fakeResponse)
        assert(result.isNotEmpty())
        assert(result == expected)
    }
}