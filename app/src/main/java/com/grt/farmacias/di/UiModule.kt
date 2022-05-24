package com.grt.farmacias.di

import com.grt.farmacias.ui.deGuardia.DeGuardiaViewModel
import com.grt.farmacias.ui.deInteres.DeInteresViewModel
import com.grt.farmacias.ui.detail.DetailViewModel
import com.grt.farmacias.ui.listadoFarmacias.FarmaciasViewModel
import com.grt.farmacias.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 *
 * Modulo en el que iniciamos todos los ViewModel que vamos a usar como Injección de dependencias
 */
val uiModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        FarmaciasViewModel(get())
    }

    viewModel {
        DeGuardiaViewModel(get())
    }

    viewModel {
        DetailViewModel()
    }

    viewModel {
        DeInteresViewModel()
    }
}