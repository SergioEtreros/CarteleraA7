package com.senkou.tv.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.senkou.tv.ui.navigation.Detail
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
      savedStateHandle.toRoute<Detail>().movieId
}
