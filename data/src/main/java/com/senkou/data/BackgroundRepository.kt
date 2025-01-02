package com.senkou.data

class BackgroundRepository(
   private val backgroundDataSource: BackgroundDataSource
) {
   suspend fun getMovieBackgroundByName(movieName: String, year: String) =
      backgroundDataSource.getMovieBackgroundByName(movieName, year)
}