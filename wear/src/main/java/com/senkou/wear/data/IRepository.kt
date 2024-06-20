package com.senkou.wear.data

import com.senkou.wear.data.model.InfoCine

interface IRepository {
   fun obtenerCartelera(): InfoCine?
}