package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.quaerense.spacewanderers.data.network.model.units.FeetDto
import org.quaerense.spacewanderers.data.network.model.units.KilometersDto
import org.quaerense.spacewanderers.data.network.model.units.MetersDto
import org.quaerense.spacewanderers.data.network.model.units.MilesDto

data class EstimatedDiameterDto(
    @SerializedName("kilometers")
    @Expose
    var kilometers: KilometersDto? = null,

    @SerializedName("meters")
    @Expose
    var meters: MetersDto? = null,

    @SerializedName("miles")
    @Expose
    var miles: MilesDto? = null,

    @SerializedName("feet")
    @Expose
    var feet: FeetDto? = null
)
