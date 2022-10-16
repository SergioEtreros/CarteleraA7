package com.senkou.carteleraa7.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.databinding.ActivityPeliculaListBinding
import com.senkou.carteleraa7.repository.ImegenesGlide
import com.senkou.carteleraa7.repository.RepoWeb
import com.senkou.carteleraa7.ui.adapters.PeliculaItemRecyclerViewAdapter

class PeliculaListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPeliculaListBinding
    private val model: PeliViewModel by viewModels()

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        model.dataA7 = DataA7(RepoWeb())

        twoPane = (binding.peliListLayout.peliculaDetailContainer != null)

        model.obtenerPeliculas().observe(this) {
                pelis ->
            if (pelis != null) {
                binding.peliListLayout.peliList.adapter = PeliculaItemRecyclerViewAdapter(this, pelis, twoPane, baseContext.getString(R.string.dia_hoy), ImegenesGlide(this))
                binding.peliListLayout.peliList.adapter?.notifyDataSetChanged()

                if (pelis.size>0) {
                    binding.progress.visibility = GONE
                }
            }
            model.obtenerDias()
//            model.obtenerPeliculasDia(baseContext.getString(R.string.dia_hoy))
        }

//        model.obtenerPeliculasDia(baseContext.getString(R.string.dia_hoy)).observe(this) { pelis ->
//            if (pelis != null) {
//                binding.peliListLayout.peliList.adapter = PeliculaItemRecyclerViewAdapter(this, pelis, twoPane)
//                binding.peliListLayout.peliList.adapter?.notifyDataSetChanged()
//
//                if (pelis.size>0) {
//                    binding.progress.visibility = GONE
//                }
//            }
//        }

        model.obtenerDias().observe(this) { dias ->
            val adapterSP = ArrayAdapter(binding.spDia.context,android.R.layout.simple_spinner_item, dias)
            binding.spDia.adapter = adapterSP
            adapterSP.notifyDataSetChanged()
        }

        // Añadimos el listener para el desplegable
        binding.spDia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val dia = if (binding.spDia.getItemAtPosition(position).toString() == baseContext.getString(R.string.dia_hoy)) {
                    ""
                } else {
                    binding.spDia.getItemAtPosition(position).toString()
                }

                binding.peliListLayout.peliList.adapter = PeliculaItemRecyclerViewAdapter(this@PeliculaListActivity, model.obtenerPeliculas().value!!, twoPane, dia, ImegenesGlide(this@PeliculaListActivity))
                binding.peliListLayout.peliList.adapter?.notifyDataSetChanged()
//                model.obtenerPeliculasDia(dia)
            }
        }
    }
}
