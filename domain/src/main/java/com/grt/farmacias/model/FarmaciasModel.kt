package com.grt.farmacias.model

import java.io.Serializable
/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 * Modelo de datos común a toda la aplicación
 */
data class FarmaciasModel(
    val id:Long=0,
    val name:String = "",
    val address:String="",
    val tlfno:String="",
    val lat:String="",
    val lon:String=""
): Serializable