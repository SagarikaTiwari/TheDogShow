package com.sagarika.data.di

import com.sagarika.data.mapper.BreedImagesEntityDataMapper
import com.sagarika.data.mapper.BreedsEntityDataMapper
import com.sagarika.data.remote.ApiClient
import com.sagarika.data.remote.ApiService
import com.sagarika.data.repository.DogRepositoryImpl
import com.sagarika.domain.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi



@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesApiService(): ApiService = ApiClient.getService()

    @Provides
    fun providesDogRepositoryAPI(
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