package com.grt.farmacias.di

import com.grt.farmacias.usecase.GetDeGuardiaUseCase
import com.grt.farmacias.usecase.GetSavedFarmaciasUseCase
import org.koin.dsl.module

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 *
 * Clase en la que definimos todos los casos de uso usados como injección de dependencias
 * en la app
 */
val usecaseModule = module {

    single { GetSavedFarmaciasUseCase(get()) }

    single { GetDeGuardiaUseCase(get()) }

}



