package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.repository.BreedRepository
import javax.inject.Inject

class DogSubBreedUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend operator fun invoke(
        breedName: String, subBreedName: String
    ): Result<List<DogBreedImage>> {
        return breedRepository.getSubBreedImages(breedName, subBreedName)
    }
}
