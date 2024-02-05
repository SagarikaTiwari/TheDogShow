package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogSubBreedUseCase @Inject constructor(private val dogBreedImagesRepository: DogBreedImagesRepository) {

    data class DogSubBreedParams(val breedName: String, val subBreedName: String)

    suspend operator fun invoke(params: DogSubBreedParams): Result<List<DogBreedImage>> {
        return dogBreedImagesRepository.getDogSubBreedImages(params.breedName, params.subBreedName)
    }
}
