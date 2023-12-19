package com.sagarika.domain.repository

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


interface DogBreedRepository {
      fun getAllBreeds(): Flow<Result<List<DogBreed>>>
}
