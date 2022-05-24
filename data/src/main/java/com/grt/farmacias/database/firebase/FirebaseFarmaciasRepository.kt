package com.grt.farmacias.database.firebase

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.grt.farmacias.model.FarmaciasModel
import com.grt.farmacias.repository.GetFarmaciasRepository
import com.grt.farmacias.utils.UtilsFarmacias
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

/**
 * Created por Gema Rosas Trujillo
 * 15/02/2022
 *
 * Clase que implementa la conexión a la BBDD Room y todas sus operaciones
 */
class FirebaseFarmaciasRepository(private val context: Context): GetFarmaciasRepository {

    private val db = FirebaseFirestore.getInstance()

    override suspend fun getFarmacias(): Result<List<FarmaciasModel>> {
        val listFarmacias = mutableListOf<FarmaciasModel>()

        runBlocking {
            var de =  db.collection("pharmacy").get()
            var result = de.await()
            for (document in result){
                val farmacia = getFarmaciaByDocument(document)

                listFarmacias.add(farmacia)
            }
        }

        return Result.success(listFarmacias.sortedBy {
            it.name
        })
    }

    override suspend fun getDeGuardia(): List<FarmaciasModel> {
        val listFarmacias = mutableListOf<FarmaciasModel>()
        var hoy = UtilsFarmacias.getToday()

        runBlocking {

            var de =  db.collection("guards").document(hoy).get()
            var result = de.await()

            var idFarmacia = result.getLong("idFarmacia")

            if (idFarmacia != null) {
                listFarmacias.add(getFarmaciaById(idFarmacia))
            }
        }

        return listFarmacias.sortedBy {
            it.name
        }
    }

    suspend fun getFarmaciaById(idFarmacia:Long): FarmaciasModel {
        var farmaciasModel = FarmaciasModel()

        runBlocking {
            var de =  db.collection("pharmacy").whereEqualTo("id", idFarmacia).get()
            var result = de.await()
            for (document in result){
                farmaciasModel = getFarmaciaByDocument(document)
            }
        }
        return farmaciasModel
    }

    fun getFarmaciaByDocument(document: QueryDocumentSnapshot):FarmaciasModel{
        var id = document.getLong("id")
        var name = document.getString("name")
        var address = document.getString("address")
        var tlfno = document.getString("tlfno")
        var location = document.getGeoPoint("location")

        return FarmaciasModel(id!!,name!!, address!!, tlfno!!, location?.latitude.toString(), location?.longitude.toString())
    }
}
