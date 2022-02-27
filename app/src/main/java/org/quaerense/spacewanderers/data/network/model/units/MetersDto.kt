package org.quaerense.spacewanderers.data.network.model.units

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetersDto(
    @SerializedName("estimated_diameter_min")
    @Expose
    var estimatedDiameterMin: Double? = null,

    @SerializedName("estimated_diameter_max")
    @Expose
    var estimatedDiameterMax: Double? = null
)