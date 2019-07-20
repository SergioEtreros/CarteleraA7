package com.senkou.carteleraa7.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.service.dreams.DreamService
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.activity_pelicula_detail.view.*
import kotlinx.android.synthetic.main.pelicula_detail.view.*
import kotlinx.android.synthetic.main.pelicula_list_content.view.*

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
//    private var item: DummyContent.PeliculaItem? = null

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

//                var pic : ImageView? = null
//                Glide.with(this).load(urlCartel).into(pic)
//
//                if (pic != null) {
//                    pic.setImageDrawable(activity?.toolbar_layout?.background)
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
            textoFicha.forEachIndexed { index, s ->
                if (index > 0)
                    builder.append("\n\n")

                if (index == textoFicha.size-1)
                    builder.append("Sinopsis:\n")

                builder.append(s)

                if (index == 0)
                {
                    builder.insert(3, "ulo")
                    if (builder.contains("Director"))
                        builder.insert(builder.indexOf("Director"), "\n")

                    if (builder.contains("Interpretes"))
                    builder.insert(builder.indexOf("Interpretes"), "\n")
                }
                else if (index == 1)
                {
                    if (builder.contains("Genero"))
                    builder.insert(builder.indexOf("Genero"), "\n")

                    if (builder.contains("Estreno"))
                    builder.insert(builder.indexOf("Estreno"), "\n")

                    if (builder.contains("Calificacion"))
                    builder.insert(builder.indexOf("Calificacion"), "\n")
                }
            }
        rootView.pelicula_detail.text = builder.toString()
        rootView.pelicula_detail.textSize = 12.0F
//        rootView.pelicula_detail.textAlignment =

        return rootView
    }

//    companion object {
//        /**
//         * The fragment argument representing the item ID that this fragment
//         * represents.
//         */
//        const val ARG_ITEM_ID = "item_id"
//    }
}
