package com.sagarika.features.presentation.ui.breedlist.fakes

import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    private var apiError: Result<List<DogBreed>>? = null
    fun getBreedListWithSuccess(): Flow<Result<List<DogBreed>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val breedList = listOf(
                DogBreed(
                    name = "hound",
                    subBreed = listOf(DogSubBreed("afd"))

                ),
                DogBreed(
                    name = "african",
                    subBreed = listOf()

                )
            )
            val apiResult = Result.Success(breedList)

            flow {
                emit(value = apiResult)
            }
        }
    }

    fun getBreedListWithNoBreeds(): Flow<Result<List<DogBreed>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val apiResult = Result.Success(emptyList<DogBreed>())
            flow {
                emit(value = apiResult)
            }
        }
    }

    fun getBreedListWithDataError(): Flow<Result<List<DogBreed>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val apiResult = Result.Error(Failure.DataError)
            flow {
                emit(value = apiResult)
            }
        }
    }
    fun getBreedListWithServerError(): Flow<Result<List<DogBreed>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val apiResult = Result.Error(Failure.ServerError)
            flow {
                emit(value = apiResult)
            }
        }
    }


}