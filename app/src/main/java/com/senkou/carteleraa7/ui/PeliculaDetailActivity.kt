package com.senkou.carteleraa7.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.senkou.carteleraa7.data.Utilidades
import com.senkou.carteleraa7.databinding.ActivityPeliculaDetailBinding
import com.senkou.carteleraa7.repository.ImegenesGlide

class PeliculaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.detailToolbar)

        intent?.let {

            val tituloPelicula = intent.getStringExtra("TITULO_PELICULA")
            val urlCartel = intent.getStringExtra("URL_CARTEL")
            val detallesPelicula = intent.getStringArrayListExtra("DETALLES_PELICULA")

            binding.cabeceraDetalle.title = tituloPelicula

            val builder = StringBuilder()
            detallesPelicula?.forEachIndexed { _, s -> builder.append(s + "\n\n") }
            binding.peliculaDetailText.text = builder.toString()
            binding.peliculaDetailText.textSize = 13.0F

            binding.fab.setOnClickListener { view ->
                Snackbar.make(view, "Abrir trailer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

                val urlTrailer = intent.getStringExtra("URL_TRAILER")

                urlTrailer?.let { Utilidades.playTrailer(this, it) }
            }

            urlCartel?.let { Utilidades.cargarImagen(ImegenesGlide(this), binding.imgCollapse, it) }
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
