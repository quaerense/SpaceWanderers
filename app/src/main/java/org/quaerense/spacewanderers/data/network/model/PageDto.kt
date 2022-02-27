package org.quaerense.spacewanderers.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PageDto(
    @SerializedName("total_elements")
    @Expose
    var totalElements: Int? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
)