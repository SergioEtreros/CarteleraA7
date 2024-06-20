package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.datasources.PlayVideoDataSource

class VideoRepository(
   private val playVideoDataSource: PlayVideoDataSource
) {
   fun playTrailer(urlTrailer: String) = playVideoDataSource.playTrailer(urlTrailer)
}