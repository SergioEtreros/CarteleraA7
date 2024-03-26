package com.senkou.carteleraa7.data.datasources

import com.senkou.carteleraa7.domain.InfoCine

interface RemoteDataSource {
   fun getCartelera(): InfoCine
}