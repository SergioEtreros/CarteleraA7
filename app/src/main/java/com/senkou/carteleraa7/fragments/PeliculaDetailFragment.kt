package com.senkou.carteleraa7.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.senkou.carteleraa7.databinding.PeliculaDetailBinding
import com.senkou.carteleraa7.util.Utilidades

class PeliculaDetailFragment : Fragment() {

    private lateinit var binding: PeliculaDetailBinding

    private var tituloPelicula :String? = null
    private var textoFicha:ArrayList<String> = ArrayList()
    private var urlTrailer :String? = null
    private var urlCartel :String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = PeliculaDetailBinding.inflate(layoutInflater)

        arguments?.let {
            if (it.containsKey("TITULO_PELICULA")) {
                tituloPelicula = it.getString("TITULO_PELICULA")
                binding.tvTituloApaisado?.text = tituloPelicula
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

        binding.fabApaisado?.setOnClickListener { view ->
            Snackbar.make(view, "Abrir trailer", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            urlTrailer?.let { Utilidades.playTrailer(requireContext(), it) }
        }

        Glide.with(this).load("https://artesiete.es$urlCartel").into(binding.imgPosterApaisado!!)

        val builder = StringBuilder()

        textoFicha.forEachIndexed { _, s -> builder.append(s + "\n\n") }


        binding.peliculaDetail.text = builder.toString()
        binding.peliculaDetail.textSize = 13.0F

        return binding.root
    }
}
