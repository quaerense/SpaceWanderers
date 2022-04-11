package org.quaerense.spacewanderers.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.database.model.AsteroidWithCloseApproachData

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asteroid: AsteroidDbModel)

    @Transaction
    @Query("SELECT * FROM asteroid")
    fun getAll(): LiveData<List<AsteroidWithCloseApproachData>>

    @Transaction
    @Query("SELECT * FROM asteroid WHERE id = :id")
    fun get(id: Int): LiveData<AsteroidWithCloseApproachData>

    @Query("DELETE FROM asteroid")
    suspend fun deleteAll()
}