package com.sagarika.data.di

import com.sagarika.data.remote.ApiService
import com.sagarika.data.repository.DogBreedImagesRepositoryImpl
import com.sagarika.data.repository.DogBreedListRepositoryImpl
import com.sagarika.domain.repository.DogBreedImagesRepository
import com.sagarika.domain.repository.DogBreedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsDogBreedListRepository(dogBreedListRepositoryImpl: DogBreedListRepositoryImpl): DogBreedRepository

    @Binds
    abstract fun bindsDogBreedImageRepository(dogBreedImagesRepositoryImpl: DogBreedImagesRepositoryImpl): DogBreedImagesRepository
}