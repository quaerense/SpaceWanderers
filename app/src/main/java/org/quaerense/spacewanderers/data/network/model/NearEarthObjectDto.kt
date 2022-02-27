package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NearEarthObjectDto(
    @SerializedName("id")
    @Expose
    var serverId: String? = null,

    @SerializedName("neo_reference_id")
    @Expose
    var neoReferenceId: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("name_limited")
    @Expose
    var nameLimited: String? = null,

    @SerializedName("designation")
    @Expose
    var designation: String? = null,

    @SerializedName("nasa_jpl_url")
    @Expose
    var nasaJplUrl: String? = null,

    @SerializedName("absolute_magnitude_h")
    @Expose
    var absoluteMagnitudeH: Double? = null,

    @SerializedName("estimated_diameter")
    @Expose
    var estimatedDiameter: EstimatedDiameterDto? = null,

    @SerializedName("is_potentially_hazardous_asteroid")
    @Expose
    var isPotentiallyHazardousAsteroid: Boolean? = null,

    @SerializedName("close_approach_data")
    @Expose
    var closeApproachData: List<CloseApproachDataDto>? = null,

    @SerializedName("orbital_data")
    @Expose
    var orbitalData: OrbitalDataDto? = null,

    @SerializedName("is_sentry_object")
    @Expose
    var isSentryObject: Boolean? = null
)