package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.repository.BreedRepository
import javax.inject.Inject

class BreedListUseCase @Inject constructor(private val dogBreedsRepository: BreedRepository) {
    suspend operator fun invoke(): Result<List<DogBreed>> {
        return dogBreedsRepository.getAllBreeds()
    }
}
