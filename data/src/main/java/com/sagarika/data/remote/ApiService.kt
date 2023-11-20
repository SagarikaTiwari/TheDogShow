package com.sagarika.data.remote


import com.sagarika.data.dto.BreedsEntity
import com.sagarika.data.dto.BreedImagesEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getBreedList(): BreedsEntity
    @GET("productDetails")
    suspend fun getBreedImages(@Query("breedName") breedName: String): BreedImagesEntity
}