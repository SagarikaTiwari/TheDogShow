package com.sagarika.features.presentation.mapper

import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DogBreedDomainToDogBreedPresentationTest {
    private lateinit var mapper : DogBreedDomainToDogBreedPresentation

    @Before
    fun setUp(){
        mapper = DogBreedDomainToDogBreedPresentation()
    }
    @Test
    fun `Given dogBreed with empty subbreed list when DogBreedDomainToDogBreedPresentation mapper is called Then it returns dogBreedpresentation with empty subbreed`() {
        val dogBreed = DogBreed(
            name = "hound",
            subBreed = emptyList()
        )
        val expected = DogBreedPresentation(
            breedNameInitial = "H",
            breedName = "Hound",
            subBreeds = emptyList()
        )
        assert(mapper.map(dogBreed) == expected)
    }

    @Test
    fun `Given dogBreed with subbreed list when DogBreedDomainToDogBreedPresentation mapper is called Then it returns dogBreedpresentation with  subbreed`() {
        val dogBreed = DogBreed(
            name = "hound",
            subBreed = listOf(DogSubBreed("afs"))
        )
        val expected = DogBreedPresentation(
            breedNameInitial = "H", breedName = "Hound",
            subBreeds = listOf(
                DogSubBreedPresentation(
                    breedName = "Afs",
                    breedNameInitial = "A",
                    parentBreedName = "Hound"
                )
            )
        )
        assert(mapper.map(dogBreed)== expected)
    }
}
