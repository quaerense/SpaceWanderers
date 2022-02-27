package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrbitalDataDto(
    @SerializedName("orbit_id")
    @Expose
    var orbitId: String? = null,

    @SerializedName("orbit_determination_date")
    @Expose
    var orbitDeterminationDate: String? = null,

    @SerializedName("first_observation_date")
    @Expose
    var firstObservationDate: String? = null,

    @SerializedName("last_observation_date")
    @Expose
    var lastObservationDate: String? = null,

    @SerializedName("data_arc_in_days")
    @Expose
    var dataArcInDays: Int? = null,

    @SerializedName("observations_used")
    @Expose
    var observationsUsed: Int? = null,

    @SerializedName("orbit_uncertainty")
    @Expose
    var orbitUncertainty: String? = null,

    @SerializedName("minimum_orbit_intersection")
    @Expose
    var minimumOrbitIntersection: String? = null,

    @SerializedName("jupiter_tisserand_invariant")
    @Expose
    var jupiterTisserandInvariant: String? = null,

    @SerializedName("epoch_osculation")
    @Expose
    var epochOsculation: String? = null,

    @SerializedName("eccentricity")
    @Expose
    var eccentricity: String? = null,

    @SerializedName("semi_major_axis")
    @Expose
    var semiMajorAxis: String? = null,

    @SerializedName("inclination")
    @Expose
    var inclination: String? = null,

    @SerializedName("ascending_node_longitude")
    @Expose
    var ascendingNodeLongitude: String? = null,

    @SerializedName("orbital_period")
    @Expose
    var orbitalPeriod: String? = null,

    @SerializedName("perihelion_distance")
    @Expose
    var perihelionDistance: String? = null,

    @SerializedName("perihelion_argument")
    @Expose
    var perihelionArgument: String? = null,

    @SerializedName("aphelion_distance")
    @Expose
    var aphelionDistance: String? = null,

    @SerializedName("perihelion_time")
    @Expose
    var perihelionTime: String? = null,

    @SerializedName("mean_anomaly")
    @Expose
    var meanAnomaly: String? = null,

    @SerializedName("mean_motion")
    @Expose
    var meanMotion: String? = null,

    @SerializedName("equinox")
    @Expose
    var equinox: String? = null,

    @SerializedName("orbit_class")
    @Expose
    var orbitClass: OrbitClassDto? = null

)
