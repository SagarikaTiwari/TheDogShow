package com.sagarika.domain.repository

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogBreedImage

interface BreedRepository {
    suspend fun getAllBreeds(): Result<List<DogBreed>>
    suspend fun getBreedImages(breedName: String): Result<List<DogBreedImage>>
    suspend fun getSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Result<List<DogBreedImage>>
}
