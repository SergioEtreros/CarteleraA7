package com.senkou.carteleraa7.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample titulo for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<PeliculaItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PeliculaItem> = HashMap()

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem("Titulo " + i, "Sesiones: 18:00 - 20:30 - 22:00"))
        }
    }

    private fun addItem(item: PeliculaItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.titulo, item)
    }

    private fun createDummyItem(titulo: String, sesiones: String): PeliculaItem {
        return PeliculaItem(titulo, sesiones, makeDetails(titulo))
    }

    private fun makeDetails(titulo: String): String {
        val builder = StringBuilder()
        builder.append("Detalles de la película: ").append(titulo)

        // TODO meter los detalles de la película
        //for (i in 0..position - 1) {
          builder.append("\n\nMás detalles e información aquí.")
        //}
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of titulo.
     */
    data class PeliculaItem(val titulo: String, val sesiones: String, val detalles: String) {
        override fun toString(): String = titulo + ":" + sesiones + " (" + detalles + ")"
    }
}
