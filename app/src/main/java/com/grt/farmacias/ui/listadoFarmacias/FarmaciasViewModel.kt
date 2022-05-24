package com.grt.farmacias.ui.listadoFarmacias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.grt.farmacias.common.BaseViewModel
import com.grt.farmacias.common.NavData
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.usecase.GetSavedFarmaciasUseCase


class FarmaciasViewModel(
    private val getSavedFarmaciasUseCase : GetSavedFarmaciasUseCase
): BaseViewModel() {

    companion object{
        const val NAV_DETAIL = 0
    }

    private val liveListFarmacias: MutableLiveData<List<FarmaciasModel>> = MutableLiveData()
    val obsListFarmacias: LiveData<List<FarmaciasModel>> = liveListFarmacias

    override fun onInitialization() {
        showLoading()
        executeUseCase(
            finalAction = {
                hideLoading()
            }
        ) {
            liveListFarmacias.value = getSavedFarmaciasUseCase.execute(Unit)
        }
    }

    // Función encargada del manejo de haber hecho click en uno de los elementos de la lista de
    // Pokemon y navega hasta la pantalla de Detalles
    fun onActionPokemonClicked(farmaciaModel: FarmaciasModel) {
        navigate(NavData(NAV_DETAIL, farmaciaModel))
    }
}