package com.sagarika.domain.repository

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import kotlinx.coroutines.flow.Flow

interface DogBreedImagesRepository {

    fun getDogBreedImages(breedName: String): Flow<Result<List<DogBreedImage>>>
    fun getDogSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Flow<Result<List<DogBreedImage>>>

}