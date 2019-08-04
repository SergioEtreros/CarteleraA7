package com.senkou.carteleraa7.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senkou.carteleraa7.R
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.pelicula_detail.view.*



/**
 * A fragment representing a single Pelicula detail screen.
 * This fragment is either contained in a [PeliculaListActivity]
 * in two-pane mode (on tablets) or a [PeliculaDetailActivity]
 * on handsets.
 */
class PeliculaDetailFragment : Fragment() {

    /**
     * The dummy titulo this fragment is presenting.
     */

    private var tituloPelicula :String? = null
    private var textoFicha:ArrayList<String> = ArrayList()
    private var urlTrailer :String? = null
    private var urlCartel :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey("TITULO_PELICULA")) {
                tituloPelicula = it.getString("TITULO_PELICULA")
                activity?.toolbar_layout?.title = tituloPelicula
            }

            if (it.containsKey("URL_CARTEL")) {
                urlCartel = it.getString("URL_CARTEL")

//                val theBitmap = Glide.with(this).load(urlCartel).asBitmap().get()
//
//                activity?.toolbar_layout?.background = kk
//
//                if (pic != null) {
////                    pic.setImageDrawable(activity?.toolbar_layout?.background)
//                    activity?.toolbar_layout?.background = pic.drawable
//                }
            }

            if (it.containsKey("DETALLES_PELICULA")) {
                textoFicha = it.getStringArrayList("DETALLES_PELICULA")
            }

            if (it.containsKey("URL_TRAILER")) {
                urlTrailer = it.getString("URL_TRAILER")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        val builder = StringBuilder()
            textoFicha.forEachIndexed { _, s ->
                builder.append(s + "\n\n")
            }
        rootView.pelicula_detail.text = builder.toString()
        rootView.pelicula_detail.textSize = 12.0F

        return rootView
    }
}
