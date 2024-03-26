package com.senkou.carteleraa7.framework.di

import com.senkou.carteleraa7.data.datasources.RemoteDataSource
import com.senkou.carteleraa7.framework.data.datasources.WebMovieDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FrameworkModule {

   @Provides
   fun remoteDataSourceProvider(): RemoteDataSource = WebMovieDatasource()
}