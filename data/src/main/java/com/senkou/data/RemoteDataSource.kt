package com.senkou.data

import com.senkou.domain.model.Cartelera

interface RemoteDataSource {
   suspend fun getCartelera(): Cartelera
}

