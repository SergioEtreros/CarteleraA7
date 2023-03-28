package com.senkou.carteleraa7.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.data.model.Pases
import com.senkou.carteleraa7.data.model.Peli
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

//@HiltViewModel
class PeliViewModel: ViewModel() {

    var dataA7: DataA7? = null

    val peliculas: MutableLiveData<MutableList<Peli>> by lazy {
        MutableLiveData<MutableList<Peli>>().also {
            cargarCartelera()
        }
    }

//    private val peliculasDia: MutableLiveData<MutableList<Peli>> by lazy {
//        MutableLiveData<MutableList<Peli>>().also {
//            it.value = arrayListOf()
//        }
//    }

    private val dias: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            it.value = arrayListOf()
        }
    }

    fun obtenerPeliculas(): MutableLiveData<MutableList<Peli>> {
        return peliculas
    }

//    fun obtenerPeliculasDia(dia: String): MutableLiveData<MutableList<Peli>> {
//        filtrarPorDia(dia)
//        return peliculasDia
//    }

//    private fun filtrarPorDia(dia: String) {
//
//        val diaFlitro = if (dia == "Hoy") {
//            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            sdf.format(Calendar.getInstance().time)
//        } else {
//            dia
//        }
//
//        if (peliculas.value != null) {
//
//            peliculasDia.value = peliculas.value!!.toMutableList()
//
//            peliculasDia.value?.forEach { peli -> }
//        }
//    }

    fun cargarCartelera(){

        viewModelScope.launch (Dispatchers.IO) {
            peliculas.postValue(dataA7?.obtenerCartelera())
        }
    }

    fun obtenerDias(): LiveData<List<String>>{
        cargarDias()
        return dias
    }

    private fun cargarDias() {
        val fechasSpinner = arrayListOf<String>()
        peliculas.value?.forEach { peli: Peli ->  peli.sesiones.forEach{ pases: Pases ->
            pases.fecha?.let {
                if (!fechasSpinner.contains(it)){
                     fechasSpinner.add(it)
                }
            }
        }}

        if (fechasSpinner.isNotEmpty()) {
            Utilidades.ordenarMeses(fechasSpinner)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val hoy = sdf.format(Calendar.getInstance().time)
            if (hoy == fechasSpinner[0]) {
                fechasSpinner[0] = "Hoy"
            } else {
                fechasSpinner.add(0, "Hoy")
            }

            dias.value=fechasSpinner
        }
    }

    fun obtenerDiasPeli (peli: Peli): List<String>?{
        val fechasSpinner = arrayListOf<String>()
        peli.sesiones.forEach{ pases: Pases ->
            pases.fecha?.let {
                if (!fechasSpinner.contains(it)){
                    fechasSpinner.add(it)
                }
            }
        }

        if (fechasSpinner.isNotEmpty()) {
            Utilidades.ordenarMeses(fechasSpinner)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val hoy = sdf.format(Calendar.getInstance().time)
            if (hoy == fechasSpinner[0]) {
                fechasSpinner[0] = "Hoy"
            } else {
                fechasSpinner.add(0, "Hoy")
            }

            dias.value=fechasSpinner
        }
        return dias.value
    }
}