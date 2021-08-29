package com.senkou.carteleraa7.clases

data class PeliX(
    val Anticipada: String,
    val Calificacion: String,
    val Cartel: String,
    val FechaEstreno: String,
    val Genero: String,
    val Hora: String,
    val ID_Distribuidora: String,
    val ID_Espectaculo: String,
    val InfoVersion: String,
    val MostrarEstreno: String,
    val NombreFormato: String,
    val Pases: List<Pase>,
    val Titulo: String
)