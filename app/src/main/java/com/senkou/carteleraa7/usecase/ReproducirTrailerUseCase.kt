package com.senkou.carteleraa7.usecase

import com.senkou.carteleraa7.data.VideoRepository

class ReproducirTrailerUseCase(
   private val repository: VideoRepository
) {
   operator fun invoke(urlTrailer: String) = repository.playTrailer(urlTrailer)
}