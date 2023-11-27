package com.sagarika.data.remote


import com.sagarika.data.dto.BreedsDTO
import com.sagarika.data.dto.BreedImagesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getBreedList(): BreedsDTO
    @GET("productDetails")
    suspend fun getBreedImages(@Query("breedName") breedName: String): BreedImagesDTO
}