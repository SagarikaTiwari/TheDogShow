package com.sagarika.features.presentation.ui.breedlist.fakes

import com.sagarika.common.Result
import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogSubBreed
import com.sagarika.features.presentation.constants.errorMsg
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    val dogBreedPresentation = DogBreedPresentation(
        breedNameInitial = "H",
        breedName = "Hound",
        subBreeds = listOf(DogSubBreedPresentation("A", "Hound", "Afd"))
    )
    val subBreedPresentation = DogSubBreedPresentation("A", "Hound", "Afd")

    fun getBreedListWithSuccess(): List<DogBreed> {

        return listOf(
            DogBreed(
                name = "hound",
                subBreed = listOf(DogSubBreed("afd"))

            ),
            DogBreed(
                name = "african",
                subBreed = listOf()

            )
        )
    }
    fun getMappedBreedList(): List<DogBreedPresentation> {

        return listOf(
            DogBreedPresentation(
                "H",
                "hound",
                listOf(DogSubBreedPresentation("A", "hound", "afd"))
            ),
              DogBreedPresentation("A", "african", emptyList())

        )
    }



    fun getBreedListWithNoBreeds(): Result<List<DogBreed>> =
        Result.Success(emptyList<DogBreed>())


    fun getException(): Result.Error {
        val exception = Exception(errorMsg)
        return Result.Error(exception)
    }


}