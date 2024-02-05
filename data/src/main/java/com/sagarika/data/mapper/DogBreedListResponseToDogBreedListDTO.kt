package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import javax.inject.Inject


class DogBreedListResponseToDogBreedListDTO @Inject constructor() {

    fun map(dogBreedResponse: DogBreedResponse): List<DogBreed> {
        return dogBreedResponse.message.entries.map {
            val subBreed = it.value.map { sub ->
                DogSubBreed(sub)
            }
            DogBreed(it.key, subBreed)
        }
    }
}

