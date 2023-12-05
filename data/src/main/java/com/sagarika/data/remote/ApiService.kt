package com.sagarika.data.remote


import com.sagarika.data.dto.BreedImagesDTO
import com.sagarika.data.dto.BreedListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/list/all")
    suspend fun getBreedList(): BreedListDTO
    @GET("breed/{breedName}/images")
    suspend fun getBreedImages(@Path("breedName") breedName: String): BreedImagesDTO
}