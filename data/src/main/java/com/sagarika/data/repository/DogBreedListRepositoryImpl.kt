package com.sagarika.data.repository

 import com.sagarika.common.Result
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.data.mapper.DogBreedListResponseToDogBreedListDTO
 import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.repository.DogBreedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogBreedListRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val dogBreedListResponseToDogBreedListDTO: DogBreedListResponseToDogBreedListDTO
) : DogBreedRepository {


    override suspend fun getAllBreeds(): Result<List<DogBreed>> =
        withContext(dispatcher) {
            try {
                service.getAllBreeds().run {
                    Result.Success(dogBreedListResponseToDogBreedListDTO.map(this))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}
