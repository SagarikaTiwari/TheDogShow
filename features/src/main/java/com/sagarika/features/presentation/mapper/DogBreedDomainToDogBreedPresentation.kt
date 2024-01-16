package com.sagarika.features.presentation.mapper


import com.sagarika.domain.model.DogBreed
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation


fun DogBreed.toPresentation(): DogBreedPresentation {
    val subBreedsPresentation = this.subBreed.map { dogBreed ->
        DogSubBreedPresentation(
            breedNameInitial = dogBreed.name[0]
                .uppercaseChar().toString(),
            parentBreedName = formatBreedName(this.name),
            breedName = formatBreedName(dogBreed.name)
        )
    }
    return DogBreedPresentation(
        breedNameInitial = this.name[0].uppercaseChar().toString(),
        breedName = formatBreedName(this.name),
        subBreeds = subBreedsPresentation
    )
}

private fun formatBreedName(name: String): String {
    return name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault()) else it.toString() }
}

