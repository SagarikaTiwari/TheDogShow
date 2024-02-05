package com.sagarika.domain.repository

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


interface DogBreedRepository {
      suspend fun getAllBreeds(): Result<List<DogBreed>>
}
