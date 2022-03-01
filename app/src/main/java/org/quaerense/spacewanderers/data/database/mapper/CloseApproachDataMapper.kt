package org.quaerense.spacewanderers.data.database.mapper

import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel
import org.quaerense.spacewanderers.data.network.model.CloseApproachDataDto

class CloseApproachDataMapper {

    fun mapListDtoToListDbModel(
        asteroidId: Long,
        closeApproachDataDto: List<CloseApproachDataDto>
    ): List<CloseApproachDataDbModel> {
        val dbModels = mutableListOf<CloseApproachDataDbModel>()

        for (dto in closeApproachDataDto) {
            dbModels.add(
                CloseApproachDataDbModel(
                    asteroidId = asteroidId,
                    epochDateCloseApproach = dto.epochDateCloseApproach,
                    relativeVelocityInKilometersPerSecond =
                    dto.relativeVelocity?.kilometersPerSecond?.toDouble()
                        ?: UNDEFINED_RELATIVE_VELOCITY,
                    relativeVelocityInKilometersPerHour =
                    dto.relativeVelocity?.kilometersPerHour?.toDouble()
                        ?: UNDEFINED_RELATIVE_VELOCITY,
                    relativeVelocityInMilesPerHour =
                    dto.relativeVelocity?.milesPerHour?.toDouble() ?: UNDEFINED_RELATIVE_VELOCITY,
                    missDistanceInAstronomical =
                    dto.missDistance?.astronomical?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInLunar =
                    dto.missDistance?.lunar?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInKilometers =
                    dto.missDistance?.kilometers?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInMiles =
                    dto.missDistance?.astronomical?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    orbitingBody = dto.orbitingBody
                )
            )
        }

        return dbModels
    }

    companion object {

        private const val UNDEFINED_RELATIVE_VELOCITY = -1.0
        private const val UNDEFINED_MISS_DISTANCE = -1.0
    }
}