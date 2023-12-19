package com.sagarika.data.repository

import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.data.dto.DogBreedImageResponse
import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.data.mapper.toDomain

import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedImagesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : DogBreedImagesRepository {

    override fun getDogBreedImages(breedName: String): Flow<Result<List<DogBreedImage>>> =
        flow {
            val res = apiService.getBreedImages(breedName)
            emit(
                when (res.isSuccessful) {
                    true -> {
                        res.body()?.let { it ->
                            if (it.status == DogBreedImageResponse.SUCCESS_STATUS) {
                                Result.Success(it.toDomain())
                            } else Result.Error(Failure.DataError)
                        } ?: Result.Error(Failure.DataError)
                    }
                    false -> Result.Error(Failure.ServerError)
                }
            )
        }

    override  fun getDogSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Flow<Result<List<DogBreedImage>>> = flow {
        val res = apiService.fetchDogSubBreedImages(breedName, subBreedName)
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
            }
        )
    }
}