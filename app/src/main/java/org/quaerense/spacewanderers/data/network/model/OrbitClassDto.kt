package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrbitClassDto(
    @SerializedName("orbit_class_type")
    @Expose
    var orbitClassType: String? = null,

    @SerializedName("orbit_class_description")
    @Expose
    var orbitClassDescription: String? = null,

    @SerializedName("orbit_class_range")
    @Expose
    var orbitClassRange: String? = null
)
