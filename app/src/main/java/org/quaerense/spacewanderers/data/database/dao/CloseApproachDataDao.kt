package org.quaerense.spacewanderers.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel

@Dao
interface CloseApproachDataDao {
    @Insert
    suspend fun insert(closeApproachData: List<CloseApproachDataDbModel>)

    @Query("DELETE FROM close_approach_data")
    suspend fun deleteAll()
}