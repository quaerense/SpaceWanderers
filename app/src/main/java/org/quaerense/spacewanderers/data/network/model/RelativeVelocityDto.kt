package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RelativeVelocityDto(
    @SerializedName("kilometers_per_second")
    @Expose
    var kilometersPerSecond: String? = null,

    @SerializedName("kilometers_per_hour")
    @Expose
    var kilometersPerHour: String? = null,

    @SerializedName("miles_per_hour")
    @Expose
    var milesPerHour: String? = null
)
