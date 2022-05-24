package com.grt.farmacias.ui.deGuardia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grt.farmacias.common.BaseViewModel
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.usecase.GetDeGuardiaUseCase
import com.grt.farmacias.usecase.GetSavedFarmaciasUseCase

class DeGuardiaViewModel(
    private val getDeGuardiaUseCase : GetDeGuardiaUseCase
): BaseViewModel() {

    private val liveListDeGuardia: MutableLiveData<List<FarmaciasModel>> = MutableLiveData()
    val obsListDeGuardia: LiveData<List<FarmaciasModel>> = liveListDeGuardia

    override fun onInitialization() {
        showLoading()
        executeUseCase(
            finalAction = {
                hideLoading()
            }
        ) {
            liveListDeGuardia.value = getDeGuardiaUseCase.execute(Unit)
        }
    }
}