package org.quaerense.spacewanderers.domain.entity

data class Asteroid(
    val name: String?,
    val absoluteMagnitudeH: String?,
    val estimatedDiameterMin: String?,
    val estimatedDiameterMax: String?,
    val isPotentiallyHazardousAsteroid: String?,
    val closeApproachData: List<CloseApproachData>
)