package com.sagarika.data.repository

import com.sagarika.common.Response
import com.sagarika.data.dto.BreedImagesEntity
import com.sagarika.data.mapper.BreedImagesEntityDataMapper
import com.sagarika.data.mapper.BreedsEntityDataMapper
import com.sagarika.data.mapper.MessageEntityDataMapper
import com.sagarika.data.remote.ApiService
import com.sagarika.domain.entities.BreedImages
import com.sagarika.domain.entities.Breeds
import com.sagarika.domain.repository.DogRepository
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val breedsEntityDataMapper: BreedsEntityDataMapper,
    private val breedImagesEntityDataMapper: BreedImagesEntityDataMapper,
) : DogRepository {

    override suspend fun getAllBreeds(): Response<Breeds> {
        return try {
            Response.Success(breedsEntityDataMapper.mapBreedsEntityToBreeds(service.getBreedList()))
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }

    override suspend fun getBreedImages(breedName: String): Response<BreedImages> {
        return try {
            Response.Success(breedImagesEntityDataMapper.mapBreedImagesEntityToBreedImages(service.getBreedImages(breedName)))
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }


}