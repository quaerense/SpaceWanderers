package org.quaerense.spacewanderers.data.database.dao

import androidx.room.*
import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.database.model.AsteroidWithCloseApproachData

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asteroid: AsteroidDbModel)

    @Transaction
    @Query("SELECT * FROM asteroid LIMIT :limit OFFSET :offset")
    suspend fun getList(limit: Int, offset: Int): List<AsteroidWithCloseApproachData>

    @Transaction
    @Query("SELECT * FROM asteroid WHERE id = :id")
    suspend fun get(id: Int): AsteroidWithCloseApproachData

    @Query("DELETE FROM asteroid")
    suspend fun deleteAll()
}