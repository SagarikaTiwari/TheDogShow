package com.sagarika.data.repository

 import com.sagarika.common.Result
import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.data.mapper.DogBreedImageResponseToDogBreedImageDTO

import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogBreedImagesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val dogBreedImageResponseToDogBreedImageDTO: DogBreedImageResponseToDogBreedImageDTO

) : DogBreedImagesRepository {

    override suspend fun getDogBreedImages(breedName: String): Result<List<DogBreedImage>> =
        withContext(dispatcher) {
            try {
                apiService.getBreedImages(breedName).run {
                    Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


    override suspend fun getDogSubBreedImages(breedName: String, subBreedName: String): Result<List<DogBreedImage>> =
        withContext(dispatcher) {
            try {
                apiService.fetchDogSubBreedImages(breedName,subBreedName).run {
                    Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


}