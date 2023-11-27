package com.sagarika.domain.repository

import com.sagarika.common.Response
import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.domain.model.BreedsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DogRepository {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<BreedsModel>

    @GET("breed/breed/images")
    suspend fun getBreedImages(@Query("breed")breedName: String): Response<BreedImagesModel>
}