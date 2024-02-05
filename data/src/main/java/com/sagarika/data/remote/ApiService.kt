package com.sagarika.data.remote


import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.data.dto.DogBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breeds/list/all/")
    suspend fun getAllBreeds():DogBreedResponse

    @GET("breed/{breed_name}/images/")
    suspend fun getBreedImages(@Path("breed_name") breedName: String):DogBreedImageResponse

    @GET("breed/{breed_name}/{sub_breed_name}/images/")
    suspend fun fetchDogSubBreedImages(
        @Path("breed_name") breedName: String,
        @Path("sub_breed_name") subBreedName: String
    ): DogBreedImageResponse
}