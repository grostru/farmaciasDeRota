package com.grt.farmacias.data.di

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dataModule = module {

    single { FirebaseFarmaciasRepository(get()) }

    single<GetFarmaciasRepository> {
        get<FirebaseFarmaciasRepository>()
    }
}