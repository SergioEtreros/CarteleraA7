package com.senkou.carteleraa7.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.senkou.carteleraa7.Clases.Cartelera
import com.senkou.carteleraa7.Clases.Pelicula
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
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

        val context = applicationContext

        var cartelera = Cartelera(context) /*TODO*/ //Meter esto en la clase APPLICATION PARA QUE SEA COMÚN
        setupRecyclerView(pelicula_list, cartelera.peliculas)
        cartelera.iniciarDatosCartelera(pelicula_list.adapter as PeliculaItemRecyclerViewAdapter)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, peliculas: MutableList<Pelicula>) {
        recyclerView.adapter = PeliculaItemRecyclerViewAdapter(this, peliculas, twoPane)
    }
}
