package com.senkou.carteleraa7.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.dummy.DummyContent
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
    private var item: DummyContent.PeliculaItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy titulo specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load titulo from a titulo provider.
                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.toolbar_layout?.title = item?.titulo
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        // Show the dummy titulo as text in a TextView.
        item?.let {
            rootView.pelicula_detail.text = it.detalles
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
