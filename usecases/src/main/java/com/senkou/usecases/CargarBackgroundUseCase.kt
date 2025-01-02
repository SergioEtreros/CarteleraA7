package com.senkou.usecases

import com.senkou.data.BackgroundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarBackgroundUseCase (
   private val repository: BackgroundRepository
) {

   suspend operator fun invoke(movieName: String, year: String) = withContext(Dispatchers.IO) {
      repository.getMovieBackgroundByName(movieName, year)
   }
}