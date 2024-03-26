package com.senkou.carteleraa7.data.repositories

import com.senkou.carteleraa7.data.datasources.RemoteDataSource
import com.senkou.carteleraa7.domain.InfoCine

class MoviesRepository(private val remoteDataSource: RemoteDataSource) {
   fun getCartelera(): InfoCine = remoteDataSource.getCartelera()
}

