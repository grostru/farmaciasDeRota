package com.grt.farmacias.common

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.grt.farmacias.data.di.dataModule
import com.grt.farmacias.di.uiModule
import com.grt.farmacias.di.usecaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 *
 * Clase en la que inciamos Koin y el contexto de la Aplicación. Esta clase siempre ha de estar
 * definida en el manifest
 */
class FarmaciasApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicialización de Admobs
        MobileAds.initialize(this)

        startKoin {
            androidContext(this@FarmaciasApplication)
            modules(
                uiModule,
                dataModule,
                usecaseModule
            )
        }
    }
}