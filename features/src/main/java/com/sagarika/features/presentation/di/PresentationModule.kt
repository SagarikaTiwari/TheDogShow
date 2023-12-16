package com.sagarika.features.presentation.di

import com.sagarika.features.presentation.mapper.BreedGalleryMapper
import com.sagarika.features.presentation.mapper.BreedListMapper
import com.sagarika.features.presentation.mapper.MessageMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
class PresentationModule {
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    @Provides
    fun providesBreedMessageMapper(): MessageMapper = MessageMapper()
    @Provides
    fun providesBreedListMapperr(messageMapper: MessageMapper): BreedListMapper =
        BreedListMapper(messageMapper)
    @Provides
    fun providesBreedGallerMapper(): BreedGalleryMapper = BreedGalleryMapper()
}
