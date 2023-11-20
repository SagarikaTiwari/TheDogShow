package com.sagarika.domain.repository

import com.sagarika.common.Response
import com.sagarika.domain.entities.BreedImages
import com.sagarika.domain.entities.Breeds
import retrofit2.http.GET
import retrofit2.http.Query

interface DogRepository {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<Breeds>

    @GET("breed/breed/images")
    suspend fun getBreedImages(@Query("breed")breedName: String): Response<BreedImages>
}