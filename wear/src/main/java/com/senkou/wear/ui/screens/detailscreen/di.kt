package com.senkou.wear.ui.screens.detailscreen

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DetailViewModelComponent {

   @Provides
   @ViewModelScoped
   @Named("movieId")
   fun provideMovieId(savedStateHandle: SavedStateHandle) =
      savedStateHandle.get<Int>("movieId") ?: -1
}
