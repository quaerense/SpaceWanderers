package org.quaerense.spacewanderers.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class AsteroidWithCloseApproachData(
    @Embedded val asteroid: AsteroidDbModel,
    @Relation(
        parentColumn = "id",
        entityColumn = "asteroidId"
    )
    val closeApproachData: List<CloseApproachDataDbModel>
)