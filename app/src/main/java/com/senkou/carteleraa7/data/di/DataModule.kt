package com.senkou.carteleraa7.data.di

import com.senkou.carteleraa7.data.datasources.RemoteDataSource
import com.senkou.carteleraa7.data.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

   @Provides
   fun MoviesRepositoryProvider(remoteDataSource: RemoteDataSource): MoviesRepository =
      MoviesRepository(remoteDataSource);
}