package com.sagarika.data.di

import com.sagarika.data.mapper.BreedImagesEntityDataMapper
import com.sagarika.data.mapper.BreedsEntityDataMapper
import com.sagarika.data.mapper.MessageEntityDataMapper
import com.sagarika.data.remote.ApiService
import com.sagarika.data.repository.DogRepositoryImpl
import com.sagarika.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    companion object {

        @Provides
        fun providesMessageMapper(): MessageEntityDataMapper = MessageEntityDataMapper()

        @Provides
        fun providesBreedListMapper(mapper: MessageEntityDataMapper): BreedsEntityDataMapper =
            BreedsEntityDataMapper(mapper)

        @Provides
        fun providesBreedImagesEntityDataMapper(): BreedImagesEntityDataMapper =
            com.sagarika.data.mapper.BreedImagesEntityDataMapper()
    }


    @Provides
    fun providesDogRepositoryImpl(
        service: ApiService,
        breedsEntityDataMapper: BreedsEntityDataMapper,
        breedImagesEntityDataMapper: BreedImagesEntityDataMapper,
    ): DogRepositoryImpl =
        DogRepositoryImpl(service, breedsEntityDataMapper, breedImagesEntityDataMapper)

    @Provides
    fun providesDogRepository(
        dogRepositoryImpl: DogRepositoryImpl
    ): DogRepository = dogRepositoryImpl


}