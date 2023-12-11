package com.sagarika.data.repository

import android.net.http.HttpException
import android.net.http.NetworkException
import android.os.Build
import com.sagarika.common.Response
import com.sagarika.data.mapper.BreedImagesEntityDataMapper
import com.sagarika.data.mapper.BreedsEntityDataMapper
import com.sagarika.data.remote.ApiService
import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.domain.model.BreedsModel
import com.sagarika.domain.repository.DogRepository
import java.io.IOException
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val breedsEntityDataMapper: BreedsEntityDataMapper,
    private val breedImagesEntityDataMapper: BreedImagesEntityDataMapper,
) : DogRepository {

    override suspend fun getAllBreeds(): Response<BreedsModel> {
        return try {
            Response.Success(breedsEntityDataMapper.mapBreedsEntityToBreeds(service.getBreedList()))
        } catch (e: IOException) {
            Response.Error(e.localizedMessage)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }

    override suspend fun getBreedImages(breedName: String): Response<BreedImagesModel> {
        return try {
            Response.Success(
                breedImagesEntityDataMapper.mapBreedImagesEntityToBreedImages(
                    service.getBreedImages(
                        breedName
                    )
                )
            )
        } catch (e: IOException) {
            Response.Error(e.localizedMessage)
        } catch (e: Exception) {
            Response.Error(e.localizedMessage)
        }
    }


}