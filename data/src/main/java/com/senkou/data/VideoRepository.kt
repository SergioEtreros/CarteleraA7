package com.senkou.data

class VideoRepository(
   private val playVideoDataSource: PlayVideoDataSource
) {
   fun playTrailer(urlTrailer: String) = playVideoDataSource.playTrailer(urlTrailer)
}