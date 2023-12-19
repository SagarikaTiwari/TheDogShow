package com.sagarika.domain.usecases

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BreedListUseCase @Inject constructor(private val dogBreedsRepository: DogBreedRepository) :
    BaseUseCase<Unit, List<DogBreed>>{


    override fun invoke(params: Unit): Flow<Result<List<DogBreed>?>> {
        return dogBreedsRepository.getAllBreeds()
    }

}
