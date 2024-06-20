package com.senkou.carteleraa7.usecase

import com.senkou.carteleraa7.data.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarCarteleraUseCase(private val repository: MoviesRepository) {
   suspend operator fun invoke() = withContext(Dispatchers.IO) {
      repository.getCartelera()
   }
}