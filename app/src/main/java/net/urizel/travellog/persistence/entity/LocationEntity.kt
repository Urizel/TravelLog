package net.urizel.travellog.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "locations"
)
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    // TODO support localization
    @ColumnInfo(name = "name")
    val name: String

    // TODO
)