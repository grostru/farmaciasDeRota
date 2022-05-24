package com.grt.farmacias.repository

import com.grt.farmacias.model.FarmaciasModel

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 * Interface del caso de uso que obtiene la lista de Farmacias de Firebase
 */
interface GetFarmaciasRepository {
    suspend fun getFarmacias(): Result<List<FarmaciasModel>>

    suspend fun getDeGuardia(): List<FarmaciasModel>
}