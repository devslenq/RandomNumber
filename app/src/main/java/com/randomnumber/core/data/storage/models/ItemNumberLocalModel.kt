package com.randomnumber.core.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.randomnumber.core.data.network.models.ItemNumberModel

@Entity
data class ItemNumberLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "date") var date: String?,
    @ColumnInfo(name = "found") var found: Boolean?,
    @ColumnInfo(name = "number") var number: Int?,
    @ColumnInfo(name = "text") var text: String?,
    @ColumnInfo(name = "type") var type: String?

) {
    fun convertToLocalModel() = ItemNumberModel(
        number = this.number,
        date = this.date,
        found = this.found,
        text = this.text,
        type = this.type
    )
}
