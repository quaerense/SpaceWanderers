package org.quaerense.spacewanderers.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid")
data class AsteroidDbModel(
    @PrimaryKey val id: Long,
    val name: String?,
    val absoluteMagnitudeH: Double?,
    val estimatedDiameterInKilometersMin: Double?,
    val estimatedDiameterInKilometersMax: Double?,
    val estimatedDiameterInMetersMin: Double?,
    val estimatedDiameterInMetersMax: Double?,
    val estimatedDiameterInMilesMin: Double?,
    val estimatedDiameterInMilesMax: Double?,
    val estimatedDiameterInFeetMin: Double?,
    val estimatedDiameterInFeetMax: Double?,
    val isPotentiallyHazardousAsteroid: Boolean?
)