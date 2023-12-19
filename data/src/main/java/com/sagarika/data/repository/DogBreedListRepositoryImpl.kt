package com.sagarika.data.repository

import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.data.mapper.toDomain
import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedListRepositoryImpl @Inject constructor(
    private val service: ApiService,
) : DogBreedRepository {

    override fun getAllBreeds(): Flow<Result<List<DogBreed>>> =
        flow {

            val res = service.getAllBreeds()
            emit(
                when (res.isSuccessful) {
                    true -> {
                        res.body()?.let { it ->
                            if (it.status == DogBreedResponse.SUCCESS_STATUS) {
                                Result.Success(it.toDomain())
                            } else Result.Error(Failure.DataError)
                        } ?: Result.Error(Failure.DataError)
                    }

                    false -> Result.Error(Failure.ServerError)
                })
        }
}


