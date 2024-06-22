package com.senkou.data

import com.senkou.domain.model.Cartelera
import com.senkou.domain.model.Sesion

interface RemoteDataSource {
   suspend fun getCartelera(): Cartelera
   suspend fun getSsiones(): List<Sesion>
}