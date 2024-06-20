package com.senkou.carteleraa7.data.datasources

import com.senkou.carteleraa7.framework.remote.model.InfoCine

interface RemoteDataSource {
   suspend fun getCartelera(): InfoCine
   suspend fun getSsiones(): InfoCine
}