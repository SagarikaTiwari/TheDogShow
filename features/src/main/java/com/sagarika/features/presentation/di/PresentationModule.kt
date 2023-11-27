package com.sagarika.features.presentation.di

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
class PresentationModule {
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
