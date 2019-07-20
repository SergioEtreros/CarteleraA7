package com.senkou.carteleraa7.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.senkou.carteleraa7.fragments.PeliculaDetailFragment
import com.senkou.carteleraa7.R
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import android.content.ActivityNotFoundException
import android.net.Uri


/**
 * An activity representing a single Pelicula detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [PeliculaListActivity].
 */
class PeliculaDetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Abrir trailer", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val videoId = intent.getStringExtra("URL_TRAILER").substringAfter("embed/")
            openYoutubeLink(videoId)
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = PeliculaDetailFragment().apply {
                arguments = Bundle().apply {
//                    putString(PeliculaDetailFragment.ARG_ITEM_ID, intent.getStringExtra(PeliculaDetailFragment.ARG_ITEM_ID))
                    putString("TITULO_PELICULA", intent.getStringExtra("TITULO_PELICULA"))
                    putStringArrayList("DETALLES_PELICULA", intent.getStringArrayListExtra("DETALLES_PELICULA"))
                    putString("URL_TRAILER", intent.getStringExtra("URL_TRAILER"))
                    putString("URL_CARTEL", intent.getStringExtra("URL_CARTEL"))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.pelicula_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    onBackPressed()
//                    navigateUpTo(Intent(this, PeliculaListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }
}
