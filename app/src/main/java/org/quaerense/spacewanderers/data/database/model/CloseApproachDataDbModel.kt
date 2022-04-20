package org.quaerense.spacewanderers.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "close_approach_data")
data class CloseApproachDataDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var asteroidId: String,
    val epochDateCloseApproach: Long?,
    val relativeVelocityInKilometersPerSecond: Double?,
    val relativeVelocityInKilometersPerHour: Double?,
    val relativeVelocityInMilesPerHour: Double?,
    val missDistanceInAstronomical: Double?,
    val missDistanceInLunar: Double?,
    val missDistanceInKilometers: Double?,
    val missDistanceInMiles: Double?,
    val orbitingBody: String?
)
