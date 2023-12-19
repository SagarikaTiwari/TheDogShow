package com.sagarika.features.presentation.mapper


import com.sagarika.domain.model.DogBreed
import com.sagarika.features.presentation.model.DogBreedPresentation
import com.sagarika.features.presentation.model.DogSubBreedPresentation


fun DogBreed.toPresentation(): DogBreedPresentation {
    val subBreedsPresentation = this.subBreed.map { dogBreed ->
        DogSubBreedPresentation(
            breedNameInitial = dogBreed.name[0]
                .uppercaseChar().toString(),
            parentBreedName = this.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault()) else it.toString() },
            breedName = dogBreed.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault()) else it.toString() }
        )
    }
    return DogBreedPresentation(
        breedNameInitial = this.name[0].uppercaseChar().toString(),
        breedName = this.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault()) else it.toString() },
        subBreeds = subBreedsPresentation
    )
}

