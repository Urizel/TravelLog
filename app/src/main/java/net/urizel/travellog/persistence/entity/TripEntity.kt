package net.urizel.travellog.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import net.urizel.travellog.persistence.converter.ZoneDateTimeConverter
import org.threeten.bp.ZonedDateTime

@Entity(
    tableName = "trips",
    indices = [Index("origin_location_id"), Index("destination_location_id")],
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("origin_location_id")
        ),
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("destination_location_id")
        )
    ]
)
@TypeConverters(ZoneDateTimeConverter::class)
data class TripEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(
        name = "origin_location_id"
    )
    val originLocationId: Long,

    @ColumnInfo(
        name = "destination_location_id"
    )
    val destinationLocationId: Long,

    @ColumnInfo(name = "departure_time")
    val departureTime: ZonedDateTime,

    @ColumnInfo(name = "arrival_time")
    val arrivalTime: ZonedDateTime
)

