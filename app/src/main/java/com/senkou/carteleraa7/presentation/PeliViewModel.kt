package com.senkou.carteleraa7.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.data.model.Pelicula
import com.senkou.carteleraa7.data.model.Sesion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//@HiltViewModel
class PeliViewModel: ViewModel() {

    var dataA7: DataA7? = null

    val peliculas: MutableLiveData<MutableList<Pelicula>> = MutableLiveData<MutableList<Pelicula>>()

    private val sesiones: MutableLiveData<MutableList<Sesion>> = MutableLiveData<MutableList<Sesion>>()

    val proximosEstrenos: MutableLiveData<MutableList<Pelicula>> = MutableLiveData<MutableList<Pelicula>>()

    val tabindex: MutableLiveData<Int> = MutableLiveData(0)

//    private val peliculasDia: MutableLiveData<MutableList<Peli>> by lazy {
//        MutableLiveData<MutableList<Peli>>().also {
//            it.value = arrayListOf()
//        }
//    }

    fun actualizarTabIndex (index: Int){
        tabindex.value = index
    }

//    fun getTabIndex(): Int{
//        return if (tabindex.value != null){
//            tabindex.value!!
//        } else {
//            0
//        }
//
//    }

//    private val dias: MutableLiveData<List<String>> by lazy {
//        MutableLiveData<List<String>>().also {
//            it.value = arrayListOf()
//        }
//    }

    fun getPeliculas() = peliculas.value.orEmpty()

    fun getSesiones(idPelicula: Int): List<Sesion> =
        sesiones.value?.filter { it.iDEspectaculo == idPelicula}.orEmpty()

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

    fun cargarCartelera() {

        viewModelScope.launch (Dispatchers.IO) {
            val response = dataA7?.obtenerCartelera()
//            val pelisAux: MutableList<Peli> = ArrayList()
//            val pelisProxAux: MutableList<ProximoEstreno> = ArrayList()
//            response?.pelis?.forEach {
//                pelisAux.add(it)
//                peliculas.postValue(pelisAux)
//                delay(500)
//            }
//
            peliculas.postValue(response?.pelis?.toMutableList())
            sesiones.postValue(response?.sesiones?.toMutableList())
            proximosEstrenos.postValue(response?.proximosEstrenos?.toMutableList())
        }
    }

//    fun obtenerDias(): LiveData<List<String>>{
//        cargarDias()
//        return dias
//    }

//    private fun cargarDias() {
//        val fechasSpinner = arrayListOf<String>()
//        sesiones.value?.forEach{ sesion ->
//            sesion.fechaEstrenoSpanish.let {
//                if (!fechasSpinner.contains(it)){
//                     fechasSpinner.add(it)
//                }
//            }
//        }
//
//        if (fechasSpinner.isNotEmpty()) {
//            Utilidades.ordenarMeses(fechasSpinner)
//
//            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            val hoy = sdf.format(Calendar.getInstance().time)
//            if (hoy == fechasSpinner[0]) {
//                fechasSpinner[0] = "Hoy"
//            } else {
//                fechasSpinner.add(0, "Hoy")
//            }
//
////            dias.value=fechasSpinner
//        }
//    }

    fun obtenerDiasSesiones (sesiones: List<Sesion>): List<String>{
        val fechasSpinner = arrayListOf<String>()
        sesiones.forEach{ sesion ->
            sesion.diacompleto.let {
                if (!fechasSpinner.contains(it)){
                    fechasSpinner.add(it)
                }
            }
        }

        if (fechasSpinner.isNotEmpty()) {
            fechasSpinner.sort()
            Utilidades.ordenarMeses(fechasSpinner)

//            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            val hoy = sdf.format(Calendar.getInstance().time)
//            if (hoy == fechasSpinner[0]) {
//                fechasSpinner[0] = "Hoy"
//            } else {
//                fechasSpinner.add(0, "Hoy")
//            }

//            dias.value=fechasSpinner
        }
        return fechasSpinner
    }
}