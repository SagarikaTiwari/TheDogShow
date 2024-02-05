package com.sagarika.features.presentation.mapper


import com.sagarika.domain.model.DogBreed
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation
import javax.inject.Inject

class DogBreedDomainToDogBreedPresentation @Inject constructor() {
    fun map(dogBreed: DogBreed): DogBreedPresentation {
        val subBreedsPresentation = dogBreed.subBreed.map { subBreed ->
            DogSubBreedPresentation(
                breedNameInitial = subBreed.name[0]
                    .uppercaseChar().toString(),
                parentBreedName = formatBreedName(dogBreed.name),
                breedName = formatBreedName(subBreed.name)
            )
        }
        return DogBreedPresentation(
            breedNameInitial = dogBreed.name[0].uppercaseChar().toString(),
            breedName = formatBreedName(dogBreed.name),
            subBreeds = subBreedsPresentation
        )
    }

    private fun formatBreedName(name: String): String {
        return name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault()) else it.toString() }
    }

}