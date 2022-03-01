package org.quaerense.spacewanderers.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel

@Dao
abstract class CloseApproachDataDao {

    suspend fun insert(
        asteroidId: Long,
        closeApproachData: List<CloseApproachDataDbModel>
    ) {
        for (closeApproach in closeApproachData) {
            closeApproach.asteroidId = asteroidId
        }

        insert(closeApproachData)
    }

    @Insert
    abstract suspend fun insert(closeApproachData: List<CloseApproachDataDbModel>)

    @Query("DELETE FROM close_approach_data")
    abstract suspend fun deleteAll()
}