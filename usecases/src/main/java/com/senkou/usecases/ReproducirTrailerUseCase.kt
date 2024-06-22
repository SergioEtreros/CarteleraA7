package com.senkou.usecases

import com.senkou.data.VideoRepository

class ReproducirTrailerUseCase(
   private val repository: VideoRepository
) {
   operator fun invoke(urlTrailer: String) = repository.playTrailer(urlTrailer)
}