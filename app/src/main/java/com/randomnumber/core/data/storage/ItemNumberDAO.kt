package com.randomnumber.core.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.randomnumber.core.data.storage.models.ItemNumberLocalModel

@Dao
interface ItemNumberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateBoundsEntity(entity: ItemNumberLocalModel)

    @Query("SELECT * FROM ItemNumberLocalModel")
    fun getBoundsEntities(): List<ItemNumberLocalModel>

}