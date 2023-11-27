package com.sagarika.domain.usecases

import com.sagarika.common.Response
import com.sagarika.domain.model.BreedsModel
import com.sagarika.domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BreedListUseCase @Inject constructor(
    private val dogRepository: DogRepository,
) {
    suspend operator fun invoke(): Response<BreedsModel> {
        return dogRepository.getAllBreeds()
    }
}

