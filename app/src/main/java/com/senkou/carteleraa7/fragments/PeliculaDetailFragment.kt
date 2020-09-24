package com.senkou.carteleraa7.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.util.Utilidades
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.pelicula_detail.view.*

class PeliculaDetailFragment : Fragment() {

    private var tituloPelicula :String? = null
    private var textoFicha:ArrayList<String> = ArrayList()
    private var urlTrailer :String? = null
    private var urlCartel :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey("TITULO_PELICULA")) {
                tituloPelicula = it.getString("TITULO_PELICULA")
                activity?.cabeceraDetalle?.title = tituloPelicula
            }

            if (it.containsKey("URL_CARTEL")) {
                urlCartel = it.getString("URL_CARTEL")
            }

            if (it.containsKey("DETALLES_PELICULA")) {
                textoFicha = it.getStringArrayList("DETALLES_PELICULA")!!
            }

            if (it.containsKey("URL_TRAILER")) {
                urlTrailer = it.getString("URL_TRAILER")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        rootView.fab_apaisado?.setOnClickListener { view ->
            Snackbar.make(view, "Abrir trailer", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            urlTrailer?.let { Utilidades.playTrailer(requireContext(), it) }
        }

        if (rootView.img_poster_apaisado!=null){
            Glide.with(requireContext()).load(urlCartel).into(rootView.img_poster_apaisado)
        }

        val builder = StringBuilder()

        textoFicha.forEachIndexed { _, s -> builder.append(s + "\n\n") }

        rootView.pelicula_detail.text = builder.toString()
        rootView.pelicula_detail.textSize = 13.0F

        return rootView
    }
}
