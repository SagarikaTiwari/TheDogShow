package com.sagarika.domain.repository

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import kotlinx.coroutines.flow.Flow

interface DogBreedImagesRepository {

    suspend fun getDogBreedImages(breedName: String): Result<List<DogBreedImage>>
    suspend fun getDogSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Result<List<DogBreedImage>>

}