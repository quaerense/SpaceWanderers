package org.quaerense.spacewanderers.data.database.mapper

import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.network.model.NearEarthObjectDto
import org.quaerense.spacewanderers.domain.entity.Asteroid
import org.quaerense.spacewanderers.domain.entity.CloseApproachData

class AsteroidMapper {

    fun mapNearEarthObjectDtoToAsteroidDbModel(dto: NearEarthObjectDto) = with(dto) {
        AsteroidDbModel(
            id = id.toLong(),
            name = name,
            absoluteMagnitudeH = absoluteMagnitudeH,
            estimatedDiameterInKilometersMin =
            estimatedDiameter?.kilometers?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
            estimatedDiameterInKilometersMax =
            estimatedDiameter?.kilometers?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
            estimatedDiameterInMetersMin =
            estimatedDiameter?.meters?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
            estimatedDiameterInMetersMax =
            estimatedDiameter?.meters?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
            estimatedDiameterInMilesMin =
            estimatedDiameter?.miles?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
            estimatedDiameterInMilesMax =
            estimatedDiameter?.miles?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
            estimatedDiameterInFeetMin =
            estimatedDiameter?.feet?.estimatedDiameterMin ?: UNDEFINED_DIAMETER,
            estimatedDiameterInFeetMax =
            estimatedDiameter?.feet?.estimatedDiameterMax ?: UNDEFINED_DIAMETER,
            isPotentiallyHazardousAsteroid = isPotentiallyHazardousAsteroid
        )
    }

    fun mapDbModelToEntity(
        dbModel: AsteroidDbModel,
        closeApproachData: List<CloseApproachData>
    ) = with(dbModel) {
        Asteroid(
            //TODO Add distance unit int parameters
            name = name,
            absoluteMagnitudeH = absoluteMagnitudeH.toString(),
            estimatedDiameterMin = estimatedDiameterInKilometersMin.toString(),
            estimatedDiameterMax = estimatedDiameterInKilometersMax.toString(),
            isPotentiallyHazardousAsteroid = parseHazardousAsteroid(isPotentiallyHazardousAsteroid),
            closeApproachData = closeApproachData
        )
    }

    private fun parseHazardousAsteroid(isPotentiallyHazardousAsteroid: Boolean?): String {
        //TODO Add string resources for return values
        if (isPotentiallyHazardousAsteroid == null) return UNDEFINED_IS_POTENTIALLY_HAZARDOUS_ASTEROID

        if (isPotentiallyHazardousAsteroid == true) {
            return "Yes"
        }

        return "No"
    }

    companion object {

        private const val UNDEFINED_DIAMETER = -1.0
        private const val UNDEFINED_IS_POTENTIALLY_HAZARDOUS_ASTEROID = "Unknown"
    }
}