package com.randomnumber.core.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.randomnumber.ApplicationConfig.database_version
import com.randomnumber.core.data.storage.models.ItemNumberLocalModel

@Database(
    entities = [ItemNumberLocalModel::class],
    version = database_version,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getItemNumberDAO(): ItemNumberDAO
}