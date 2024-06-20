package com.senkou.wear.data

import com.senkou.wear.data.model.InfoCine

class DataA7(private val repo: IRepository) {

   fun obtenerCartelera(): InfoCine? {
      return repo.obtenerCartelera()
   }
}