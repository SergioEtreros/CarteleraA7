package com.senkou.carteleraa7.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.activities.PeliculaDetailActivity
import com.senkou.carteleraa7.activities.PeliculaListActivity
import com.senkou.carteleraa7.clases.Peli
import com.senkou.carteleraa7.fragments.PeliculaDetailFragment
import kotlinx.android.synthetic.main.card_pelicula.view.*
import java.text.SimpleDateFormat
import java.util.*

class PeliculaItemRecyclerViewAdapter(private val parentActivity: PeliculaListActivity,
                                      private val values: MutableList<Peli>,
                                      private val twoPane: Boolean,
                                      private val dia:String) :
        RecyclerView.Adapter<PeliculaItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Peli
            if (twoPane) {
                val fragment = PeliculaDetailFragment().apply {
                    arguments = Bundle().apply {
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
                .inflate(R.layout.card_pelicula, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        holder.titulo.text = item.titulo
        holder.titulo.isSelected = true
        holder.horarios.text = item.crearTextoSesiones(dia)
        Glide.with(this.parentActivity).load(item.urlImagen).into(holder.image)

        val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fecha = parser.parse(parser.format(Date()))
        val fechaEstreno = parser.parse(item.fechaEstreno)

        if (fechaEstreno!! >= fecha!!) {
            holder.fecha.text = item.fechaEstreno
            holder.fecha.visibility = View.VISIBLE
        }else{
            holder.fecha.visibility = View.GONE
        }

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.id_text
        val horarios: TextView = view.id_horarios
        val image: ImageView = view.id_image
        val fecha: TextView = view.id_fecha_estreno
    }
}
