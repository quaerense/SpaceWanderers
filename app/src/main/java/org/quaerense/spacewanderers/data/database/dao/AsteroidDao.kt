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
    @Insert
    abstract suspend fun insert(asteroid: AsteroidDbModel)

    @Transaction
    @Query("SELECT * FROM asteroid")
    abstract suspend fun getAsteroids(): List<AsteroidWithCloseApproachData>

    @Query("DELETE FROM asteroid")
    abstract suspend fun deleteAll()
}