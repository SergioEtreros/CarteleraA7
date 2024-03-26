package com.senkou.carteleraa7.usecase

import com.senkou.carteleraa7.data.repositories.MoviesRepository
import com.senkou.carteleraa7.domain.InfoCine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CargarCartelera(private val repository: MoviesRepository) {
   suspend fun invoke(): InfoCine = withContext(Dispatchers.IO) {
      repository.getCartelera()
   }
}