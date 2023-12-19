package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogSubBreedUseCase @Inject constructor(private val dogBreedImagesRepository: DogBreedImagesRepository) :
    BaseUseCase<DogSubBreedUseCase.DogSubBreedParams, List<DogBreedImage>> {

    data class DogSubBreedParams(val breedName: String, val subBreedName: String)

    override fun invoke(params: DogSubBreedParams): Flow<Result<List<DogBreedImage>?>> {
        return dogBreedImagesRepository.getDogSubBreedImages(params.breedName, params.subBreedName)
    }
}
