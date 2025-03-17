package com.sagarika.data.repository

 import com.sagarika.common.Result
 import com.sagarika.data.mapper.DogBreedImageResponseToDogBreedImageDTO
 import com.sagarika.data.mapper.DogBreedListResponseToDogBreedListDTO

 import com.sagarika.data.remote.ApiService
 import com.sagarika.domain.model.DogBreed
 import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.BreedRepository
import kotlinx.coroutines.CoroutineDispatcher
 import kotlinx.coroutines.withContext
import javax.inject.Inject

class BreedRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val dogBreedImageResponseToDogBreedImageDTO: DogBreedImageResponseToDogBreedImageDTO,
    private val dogBreedListResponseToDogBreedListDTO: DogBreedListResponseToDogBreedListDTO,

) : BreedRepository {

    override suspend fun getBreedImages(breedName: String): Result<List<DogBreedImage>> =
        withContext(dispatcher) {
            try {
                apiService.getBreedImages(breedName).run {
                    Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(
                  this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }


    override suspend fun getSubBreedImages(breedName: String, subBreedName: String): Result<List<DogBreedImage>> =
        withContext(dispatcher) {
            try {
                apiService.fetchDogSubBreedImages(breedName,subBreedName).run {
                    Result.Success(dogBreedImageResponseToDogBreedImageDTO.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getAllBreeds(): Result<List<DogBreed>> =
        withContext(dispatcher) {
            try {
                apiService.getAllBreeds().run {
                    Result.Success(dogBreedListResponseToDogBreedListDTO.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}