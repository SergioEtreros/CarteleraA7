package com.senkou.data

import javax.inject.Inject

class VideoRepository @Inject constructor(
   private val playVideoDataSource: PlayVideoDataSource
) {
   fun playTrailer(urlTrailer: String) = playVideoDataSource.playTrailer(urlTrailer)
}