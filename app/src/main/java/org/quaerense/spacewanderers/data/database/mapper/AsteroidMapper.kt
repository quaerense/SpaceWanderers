package org.quaerense.spacewanderers.data.database.mapper

import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.network.model.NearEarthObjectDto

class AsteroidMapper {

    fun mapNearEarthObjectDtoToAsteroidDbModel(dto: NearEarthObjectDto) = AsteroidDbModel(
        id = dto.id.toLong(),
        name = dto.name,
        absoluteMagnitudeH = dto.absoluteMagnitudeH,
        estimatedDiameterInKilometersMin =
        dto.estimatedDiameter?.kilometers?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
        estimatedDiameterInKilometersMax =
        dto.estimatedDiameter?.kilometers?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
        estimatedDiameterInMetersMin =
        dto.estimatedDiameter?.meters?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
        estimatedDiameterInMetersMax =
        dto.estimatedDiameter?.meters?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
        estimatedDiameterInMilesMin =
        dto.estimatedDiameter?.miles?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
        estimatedDiameterInMilesMax =
        dto.estimatedDiameter?.miles?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
        estimatedDiameterInFeetMin =
        dto.estimatedDiameter?.feet?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
        estimatedDiameterInFeetMax =
        dto.estimatedDiameter?.feet?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
        isPotentiallyHazardousAsteroid = dto.isPotentiallyHazardousAsteroid
    )

    companion object {

        private const val UNDEFINED_DIAMETER = -1.0
    }
}