package com.sagarika.features.presentation.ui.breedgallery.fakes

import com.sagarika.common.Failure
import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.model.DogSubBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeGalleryData {

    private var apiError: Result<List<DogBreedImage>>? = null
    fun getBreedGalleryWithSuccess(): Flow<Result<List<DogBreedImage>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val dogBreedImages = listOf(
                DogBreedImage(
                    imageUrl = "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg"
                )
            )
            val apiResult = Result.Success(dogBreedImages)

            flow {
                emit(value = apiResult)
            }
        }
    }

    fun getEmptyBreedGallery(): Flow<Result<List<DogBreedImage>>> {
        return apiError?.let {
            flow {
                emit(
                    it,
                )
            }
        } ?: run {

            val apiResult = Result.Success(emptyList<DogBreedImage>())
            flow {
                emit(value = apiResult)
            }
        }
    }

    fun getBreedGalleryWithDataError(): Flow<Result<List<DogBreedImage>>> {
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
    fun getBreedGalleryWithServerError(): Flow<Result<List<DogBreedImage>>> {
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