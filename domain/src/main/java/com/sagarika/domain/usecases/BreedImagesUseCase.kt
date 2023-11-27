package com.sagarika.domain.usecases

import com.sagarika.common.Response
import com.sagarika.domain.model.BreedImagesModel
import com.sagarika.domain.model.BreedsModel
import com.sagarika.domain.repository.DogRepository
import javax.inject.Inject

class BreedImagesUseCase  @Inject constructor(
    private val dogRepository: DogRepository,
) {
    suspend operator fun invoke(breedName : String): Response<BreedImagesModel> {
        return dogRepository.getBreedImages(breedName)
    }
}

