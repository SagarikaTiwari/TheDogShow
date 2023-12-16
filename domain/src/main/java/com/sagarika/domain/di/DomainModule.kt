package com.sagarika.domain.di

import com.sagarika.domain.repository.DogRepository
import com.sagarika.domain.usecases.BreedImagesUseCase
import com.sagarika.domain.usecases.BreedListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {


    @Provides
    fun providesBreedListUseCase(dogRepository: DogRepository): BreedListUseCase = BreedListUseCase(dogRepository)
    @Provides
    fun providesBreedImagesUsecase(dogRepository: DogRepository): BreedImagesUseCase = BreedImagesUseCase(dogRepository)
}