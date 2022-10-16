package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.data_clases.Peli

class DataA7(private val repo: IRepository) {

    fun obtenerCartelera():MutableList<Peli>{
        return repo.obtenerCartelera()
    }
}