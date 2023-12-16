package com.sagarika.domain.repository

import com.sagarika.common.Response
import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.domain.model.BreedsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DogRepository {

    suspend fun getAllBreeds(): Response<BreedsModel>
    suspend fun getBreedImages(breedName: String): Response<BreedImagesModel>
}