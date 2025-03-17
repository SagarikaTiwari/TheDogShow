package com.sagarika.data.di

import com.sagarika.data.repository.BreedRepositoryImpl
import com.sagarika.domain.repository.BreedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsDogBreedImageRepository(dogBreedImagesRepositoryImpl: BreedRepositoryImpl): BreedRepository
}