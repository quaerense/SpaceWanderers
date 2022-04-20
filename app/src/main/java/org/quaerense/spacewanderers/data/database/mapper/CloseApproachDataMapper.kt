package org.quaerense.spacewanderers.data.database.mapper

import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel
import org.quaerense.spacewanderers.data.network.model.CloseApproachDataDto
import org.quaerense.spacewanderers.domain.entity.CloseApproachData
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CloseApproachDataMapper {

    fun mapListDtoToListDbModel(
        asteroidId: String,
        closeApproachDataDto: List<CloseApproachDataDto>
    ): List<CloseApproachDataDbModel> {
        val dbModels = mutableListOf<CloseApproachDataDbModel>()

        for (dto in closeApproachDataDto) with(dto) {
            dbModels.add(
                CloseApproachDataDbModel(
                    asteroidId = asteroidId,
                    epochDateCloseApproach = epochDateCloseApproach,
                    relativeVelocityInKilometersPerSecond =
                    relativeVelocity?.kilometersPerSecond?.toDouble()
                        ?: UNDEFINED_RELATIVE_VELOCITY,
                    relativeVelocityInKilometersPerHour =
                    relativeVelocity?.kilometersPerHour?.toDouble()
                        ?: UNDEFINED_RELATIVE_VELOCITY,
                    relativeVelocityInMilesPerHour =
                    relativeVelocity?.milesPerHour?.toDouble() ?: UNDEFINED_RELATIVE_VELOCITY,
                    missDistanceInAstronomical =
                    missDistance?.astronomical?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInLunar =
                    missDistance?.lunar?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInKilometers =
                    missDistance?.kilometers?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    missDistanceInMiles =
                    missDistance?.astronomical?.toDouble() ?: UNDEFINED_MISS_DISTANCE,
                    orbitingBody = orbitingBody
                )
            )
        }

        return dbModels
    }

    fun mapListDbModelToEntity(dbModel: List<CloseApproachDataDbModel>): List<CloseApproachData> {
        //TODO Add distance unit int parameters
        val entities = mutableListOf<CloseApproachData>()
        for (model in dbModel) {
            entities.add(
                CloseApproachData(
                    closeApproachDate = convertTimestampToTime(model.epochDateCloseApproach),
                    relativeVelocity = model.relativeVelocityInKilometersPerHour.toString(),
                    missDistance = model.missDistanceInKilometers.toString(),
                    orbitingBody = model.orbitingBody
                )
            )
        }

        return entities
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()

        return sdf.format(date)
    }

    companion object {

        private const val UNDEFINED_RELATIVE_VELOCITY = -1.0
        private const val UNDEFINED_MISS_DISTANCE = -1.0
    }
}