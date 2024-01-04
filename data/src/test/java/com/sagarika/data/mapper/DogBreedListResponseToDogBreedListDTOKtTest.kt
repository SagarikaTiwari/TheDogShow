package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import org.junit.Assert.*
import org.junit.Test

@Test
fun `dogBreedResponse to domain should return empty list`() {
    val dogBreedResponse = DogBreedResponse(
        status = "success",
        message =
        mapOf()
    )
    assert(dogBreedResponse.toDomain().isEmpty())
}

@Test
fun `dogBreedResponse to domain should return list`() {
    val dogBreedResponse = DogBreedResponse(
        status = "success",
        message =
        mapOf(
            "hound" to listOf("afghan", "basset"),
            "australian" to listOf("shepherd")
        )
    )
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
    assert(dogBreedResponse.toDomain().isNotEmpty())
    assert(dogBreedResponse.toDomain() == expected)
}