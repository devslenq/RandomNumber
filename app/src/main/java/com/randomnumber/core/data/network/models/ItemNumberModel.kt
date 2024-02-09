package com.randomnumber.core.data.network.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.randomnumber.core.data.storage.models.ItemNumberLocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemNumberModel(
    @SerializedName("date")
    var date: String?,
    @SerializedName("found")
    var found: Boolean?,
    @SerializedName("number")
    var number: Int?,
    @SerializedName("text")
    var text: String?,
    @SerializedName("type")
    var type: String?
): Parcelable {
    fun convertToLocalModel() = ItemNumberLocalModel(
        date = this.date,
        found = this.found,
        number = this.number,
        text = this.text,
        type = this.type
    )
}