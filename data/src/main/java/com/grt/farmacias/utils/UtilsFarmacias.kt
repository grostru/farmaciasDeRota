package com.grt.farmacias.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

/**
 * Created por Gema Rosas Trujillo
 * 16/02/2022
 *
 * Clase en el que se definen algunos métodos que pueden ser de utilidad a toda la app
 */
object UtilsFarmacias  {

    val tlfnoSaludResponde = 955545060
    val latCentroSalud = 36.624121752009124
    val lonCentroSalud = -6.363439996728679

    fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }

    fun getToday(): String{
        var hoy = ""
        var hora = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var formatterDia = DateTimeFormatter.ofPattern("ddMM")
            var formatterHora = DateTimeFormatter.ofPattern("HH")
            hora = LocalDateTime.now(ZoneId.systemDefault()).format(formatterHora)
            hoy = LocalDateTime.now().format(formatterDia)
            if (hora.toInt()>=20)
                hoy = LocalDateTime.now().plusDays(1).format(formatterDia)

        } else {
            val sdfDia = SimpleDateFormat("ddMM")
            val sdfHora = SimpleDateFormat("HH")
            var date: Date = Calendar.getInstance().time
            hora = sdfHora.format(date)

            if (hora.toInt()>=20) {
                val calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_MONTH,1)
                date = calendar.time
            }

            hoy = sdfDia.format(date)
        }

        return hoy
    }
}