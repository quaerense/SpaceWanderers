package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MissDistanceDto(
    @SerializedName("astronomical")
    @Expose
    var astronomical: String? = null,

    @SerializedName("lunar")
    @Expose
    var lunar: String? = null,

    @SerializedName("kilometers")
    @Expose
    var kilometers: String? = null,

    @SerializedName("miles")
    @Expose
    var miles: String? = null
)
