package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.DogBreedImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreedImagesUseCase @Inject constructor(private val dogBreedImagesRepository: DogBreedImagesRepository) :
    BaseUseCase<String, List<DogBreedImage>> {

    override fun invoke(params: String): Flow<Result<List<DogBreedImage>?>> {
        return dogBreedImagesRepository.getDogBreedImages(params)
    }
}