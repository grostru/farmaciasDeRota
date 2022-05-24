package com.grt.farmacias.data.di

import com.grt.farmacias.database.firebase.FirebaseFarmaciasRepository
import com.grt.farmacias.repository.GetFarmaciasRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 *
 * Modulo de Datos del inyector de Dependencias
 */
val dataModule = module {

    single { FirebaseFarmaciasRepository(get()) }

    single<GetFarmaciasRepository> {
       get<FirebaseFarmaciasRepository>()
    }
}