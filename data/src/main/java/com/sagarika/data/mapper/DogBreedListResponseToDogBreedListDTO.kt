package com.sagarika.data.mapper

import com.sagarika.data.dto.DogBreedResponse
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed



fun DogBreedResponse.toDomain(): List<DogBreed> {
    return this.message.entries.map {
        val subBreed = it.value.map { sub ->
            DogSubBreed(sub)
        }
        DogBreed(it.key, subBreed)
    }
}
