package com.grt.farmacias.ui.detail

import androidx.lifecycle.MutableLiveData
import com.grt.farmacias.common.BaseViewModel
import com.grt.farmacias.model.FarmaciasModel

/**
 * Created por Gema Rosas Trujillo
 * 28/01/2022
 *
 * ViewModel del Detalle de la lista de farmacias. Aquí manejamos los Livedata tanto de la farmacia
 * si ha cambiado su estado como del botón favorito y su correspondiente modificación en la
 * base de datos
 */
class DetailViewModel : BaseViewModel() {

    private val liveFarmacia = MutableLiveData<FarmaciasModel>()
    val obsFarmacia = liveFarmacia

    fun onAttachFarmacia(farmaciaModel: FarmaciasModel) {
        liveFarmacia.value = farmaciaModel
    }

    override fun onInitialization() {}
}