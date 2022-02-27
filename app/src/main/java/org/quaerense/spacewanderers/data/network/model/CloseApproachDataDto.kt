package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CloseApproachDataDto(
    @SerializedName("close_approach_date")
    @Expose
    var closeApproachDate: String? = null,

    @SerializedName("close_approach_date_full")
    @Expose
    var closeApproachDateFull: String? = null,

    @SerializedName("epoch_date_close_approach")
    @Expose
    var epochDateCloseApproach: Long? = null,

    @SerializedName("relative_velocity")
    @Expose
    var relativeVelocity: RelativeVelocityDto? = null,

    @SerializedName("miss_distance")
    @Expose
    var missDistance: MissDistanceDto? = null,

    @SerializedName("orbiting_body")
    @Expose
    var orbitingBody: String? = null
)
