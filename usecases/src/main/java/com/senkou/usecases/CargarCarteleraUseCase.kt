package com.senkou.usecases

import com.senkou.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarCarteleraUseCase(private val repository: MoviesRepository) {
   suspend operator fun invoke() = withContext(Dispatchers.IO) {
      repository.getCartelera()
   }
}