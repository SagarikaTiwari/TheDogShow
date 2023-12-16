package com.sagarika.data.remote


import com.sagarika.common.Response
import com.sagarika.data.dto.BreedImagesDTO
import com.sagarika.data.dto.BreedListDTO
import com.sagarika.domain.model.BreedImagesModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/list/all")
    suspend fun getBreedList(): BreedListDTO
    @GET("breed/breed/images")
    suspend fun getBreedImages(@Query("breed") breedName: String): BreedImagesDTO
}