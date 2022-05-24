package com.grt.farmacias.usecase

import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.repository.GetFarmaciasRepository

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 * Caso de uso que obtiene del repositorio la lista de farmacias o en su defecto una lista vacía
 */
class GetSavedFarmaciasUseCase(
    private val farmaciasRepository: GetFarmaciasRepository
): UseCase<Unit, List<FarmaciasModel>>() {

    override suspend fun executeUseCase(input: Unit):List<FarmaciasModel> {
        return farmaciasRepository.getFarmacias().getOrDefault(emptyList())
    }
}