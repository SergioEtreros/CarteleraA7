package com.senkou.carteleraa7.activities

import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import com.senkou.carteleraa7.clases.Cartelera
import kotlinx.android.synthetic.main.activity_pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PeliculaDetailActivity] representing
 * item sesiones. On tablets, the activity presents the list of items and
 * item sesiones side-by-side using two vertical panes.
 */
class PeliculaListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private val cartelera = Cartelera()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (pelicula_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        // Añadimos el listener para el desplegable
        spDia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = if (spDia.getItemAtPosition(position).toString() == baseContext.getString(R.string.dia_hoy)) "" else spDia.getItemAtPosition(position).toString()
                iniciarRecycler(progress, item)
            }
        }
    }

    private fun iniciarRecycler(progreso:ProgressBar, dia:String){
        peli_list.adapter = PeliculaItemRecyclerViewAdapter(this, cartelera.peliculas, twoPane, dia)
        cartelera.peliculas.clear()
        cartelera.iniciarDatosCartelera(peli_list.adapter as PeliculaItemRecyclerViewAdapter, progreso, spDia)
    }
}
