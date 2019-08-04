package com.senkou.carteleraa7.adapters

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.senkou.carteleraa7.Clases.Peli
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.activities.PeliculaDetailActivity
import com.senkou.carteleraa7.activities.PeliculaListActivity
import com.senkou.carteleraa7.fragments.PeliculaDetailFragment
import kotlinx.android.synthetic.main.pelicula_list_content.view.*

class PeliculaItemRecyclerViewAdapter(private val parentActivity: PeliculaListActivity,
                                      private val values: MutableList<Peli>,
                                      private val twoPane: Boolean) :
        RecyclerView.Adapter<PeliculaItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Peli
            if (twoPane) {
                val fragment = PeliculaDetailFragment().apply {
                    arguments = Bundle().apply {
//                        putString(PeliculaDetailFragment.ARG_ITEM_ID, item.tituloPelicula)
                        putString("TITULO_PELICULA", item.titulo)
                        putStringArrayList("DETALLES_PELICULA", item.textoFicha)
                        putString("URL_TRAILER", item.video)
                        putString("URL_CARTEL", item.urlImagen)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.pelicula_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, PeliculaDetailActivity::class.java).apply {
//                    putExtra(PeliculaDetailFragment.ARG_ITEM_ID, item.tituloPelicula)
                    putExtra("TITULO_PELICULA", item.titulo)
                    putExtra("DETALLES_PELICULA", item.textoFicha)
                    putExtra("URL_TRAILER", item.video)
                    putExtra("URL_CARTEL", item.urlImagen)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pelicula_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.titulo
        holder.contentView.text = item.crearTextoSesiones()
        Glide.with(this.parentActivity).load(item.urlImagen).into(holder.image)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.id_horarios
        val image: ImageView = view.id_image
    }
}
