package com.senkou.carteleraa7.framework.ui.mainscreen

import com.senkou.carteleraa7.data.repositories.MoviesRepository
import com.senkou.carteleraa7.usecase.CargarCartelera
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

   @Provides
   fun cargarCarteleraProvider(moviesRepository: MoviesRepository) =
      CargarCartelera(moviesRepository)
}