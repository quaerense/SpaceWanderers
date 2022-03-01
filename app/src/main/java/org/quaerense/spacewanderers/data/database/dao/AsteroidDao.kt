package org.quaerense.spacewanderers.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.database.model.AsteroidWithCloseApproachData
import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel

@Dao
abstract class AsteroidDao {

    fun insertAsteroidWithCloseApproachData(
        asteroid: AsteroidDbModel,
        closeApproachData: List<CloseApproachDataDbModel>
    ) {

        for (data in closeApproachData) {
            data.asteroidId = asteroid.id
        }

        insertAsteroid(asteroid)
        insertCloseApproachData(closeApproachData)
    }

    @Insert
    abstract fun insertAsteroid(asteroid: AsteroidDbModel)

    @Insert
    abstract fun insertCloseApproachData(closeApproachData: List<CloseApproachDataDbModel>)

    @Query("DELETE FROM asteroid")
    abstract fun deleteAllFromAsteroid()

    @Query("DELETE FROM close_approach_data")
    abstract fun deleteAllFromCloseApproachData()

    @Transaction
    @Query("SELECT * FROM asteroid")
    abstract fun getAsteroidWithCloseApproachData(): List<AsteroidWithCloseApproachData>
}