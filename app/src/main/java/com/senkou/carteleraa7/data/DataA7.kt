package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.Peli

class DataA7(private val repo: IRepository) {

    fun obtenerCartelera():MutableList<Peli>{
        return repo.obtenerCartelera()
    }
}