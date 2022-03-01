package org.quaerense.spacewanderers.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.quaerense.spacewanderers.data.database.dao.AsteroidDao
import org.quaerense.spacewanderers.data.database.model.AsteroidDbModel
import org.quaerense.spacewanderers.data.database.model.CloseApproachDataDbModel

@Database(
    entities = [AsteroidDbModel::class, CloseApproachDataDbModel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao

    companion object {

        private const val DB_NAME = "asteroid.db"
        private val LOCK = Any()

        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            db?.let {
                return it
            }

            synchronized(LOCK) {
                db?.let {
                    return it
                }

                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance

                return instance
            }
        }
    }
}