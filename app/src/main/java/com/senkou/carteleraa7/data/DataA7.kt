package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.InfoCine

class DataA7(private val repo: IRepository) {

    fun obtenerCartelera(): InfoCine? {
        return repo.obtenerCartelera()
    }
}