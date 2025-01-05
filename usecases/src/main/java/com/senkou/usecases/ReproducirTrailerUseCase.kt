package com.senkou.usecases

import com.senkou.data.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReproducirTrailerUseCase(
   private val repository: VideoRepository
) {
   suspend operator fun invoke(urlTrailer: String) = withContext(Dispatchers.Default){repository.playTrailer(urlTrailer)}
}