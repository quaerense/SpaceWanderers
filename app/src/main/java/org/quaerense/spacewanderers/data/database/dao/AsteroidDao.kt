package org.quaerense.spacewanderers.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.database.model.AsteroidWithCloseApproachData

@Dao
interface AsteroidDao {
    @Insert
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