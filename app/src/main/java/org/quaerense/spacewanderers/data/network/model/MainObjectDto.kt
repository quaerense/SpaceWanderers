package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainObjectDto(
    @SerializedName("page")
    @Expose
    var page: PageDto? = null,

    @SerializedName("near_earth_objects")
    @Expose
    var nearEarthObjects: List<NearEarthObjectDto>? = null

)