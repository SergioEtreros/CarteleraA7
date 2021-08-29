package com.senkou.carteleraa7.activities

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import com.senkou.carteleraa7.clases.Cartelera
import com.senkou.carteleraa7.clases.Pases
import com.senkou.carteleraa7.clases.Peli
import com.senkou.carteleraa7.databinding.ActivityPeliculaListBinding
import com.senkou.carteleraa7.util.Utilidades
import java.text.SimpleDateFormat
import java.util.*

class PeliculaListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPeliculaListBinding

    private var twoPane: Boolean = false
    private val cartelera = object : Cartelera(this) {
        override fun rellenarSpinnerDia() {
            if (binding.spDia.adapter.count <= 1){
                val fechasSpinner = arrayListOf<String>()
                peliculas.forEach { peli: Peli ->  peli.sesiones.forEach{ pases: Pases ->
                    if (!fechasSpinner.contains(pases.fecha)){
                        fechasSpinner.add(pases.fecha)
                    }
                }}

                Utilidades.ordenarMeses(fechasSpinner)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val hoy = sdf.format(Calendar.getInstance().time)
                if (hoy == fechasSpinner[0]) {
                    fechasSpinner[0] = binding.spDia.context.getString(R.string.dia_hoy)
                }else{
                    fechasSpinner.add(0, binding.spDia.context.getString(R.string.dia_hoy))
                }


                val adapterSP = ArrayAdapter(binding.spDia.context,
                    android.R.layout.simple_spinner_item, fechasSpinner)
                binding.spDia.adapter = adapterSP
                adapterSP.notifyDataSetChanged()
            }
        }

        override fun notificarInsercion(posicion: Int) {
            runOnUiThread {
                binding.progress.visibility = GONE
            }
            binding.peliListLayout.peliList.adapter?.notifyItemInserted(posicion)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        twoPane = (binding.peliListLayout.peliculaDetailContainer != null)


        // Añadimos el listener para el desplegable
        binding.spDia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = if (binding.spDia.getItemAtPosition(position).toString() == baseContext.getString(R.string.dia_hoy)) ""
                            else binding.spDia.getItemAtPosition(position).toString()
                iniciarRecycler(binding.progress, item)
            }
        }
    }

    private fun iniciarRecycler(progreso:ProgressBar, dia:String){
//        cartelera.peliculas.clear()
        val adapter = PeliculaItemRecyclerViewAdapter(this, cartelera.peliculas, twoPane, dia)
        binding.peliListLayout.peliList.adapter = adapter
        if (cartelera.peliculas.isEmpty()) {
            cartelera.iniciarDatosCartelera(adapter , progreso)
        }
    }
}
