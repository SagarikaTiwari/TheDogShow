package com.sagarika.domain.di

import com.sagarika.domain.model.DogBreed
import com.sagarika.domain.model.DogBreedImage
import com.sagarika.domain.usecases.BaseUseCase
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.BreedListUseCase
import com.sagarika.domain.usecases.DogSubBreedUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {


//    @Binds
//    abstract fun bindsBreedListUseCase(breedListUseCase: BreedListUseCase): BaseUseCase<Unit, List<DogBreed>>
//
//    @Binds
//    abstract fun bindsDogBreedImagesUseCase(breedImagesUseCase: BreedImagesUseCase): BaseUseCase<String, List<DogBreedImage>>
//
//    @Binds
//    abstract fun bindsDogSubBreedUseCase(breedUseCase: DogSubBreedUseCase): BaseUseCase<DogSubBreedUseCase.DogSubBreedParams, List<DogBreedImage>>

}
