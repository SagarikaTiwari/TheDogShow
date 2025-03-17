package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.BreedRepository
import javax.inject.Inject

class BreedImagesUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend operator fun invoke(params: String): Result<List<DogBreedImage>> {
        return breedRepository.getBreedImages(params)
    }
}