package com.grt.farmacias.database.firebase

import com.grt.farmacias.model.FarmaciasModel

data class Response(
    var farmacias: List<FarmaciasModel>? = null,
    var exception: Exception? = null
)