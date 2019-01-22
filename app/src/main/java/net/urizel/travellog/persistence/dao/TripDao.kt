package net.urizel.travellog.persistence.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import net.urizel.travellog.persistence.entity.LocationEntity
import net.urizel.travellog.persistence.entity.TripEntity

@Dao
abstract class TripDao(db: RoomDatabase) {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addTrip(trip: TripEntity): Long

    @Delete()
    abstract fun deleteTrip(trip: TripEntity)

    @Query("SELECT * FROM trips WHERE id = :id LIMIT 1")
    abstract fun getTripById(id: Long): TripEntity

    @Query("SELECT * FROM trips")
    abstract fun getTrips(): List<TripEntity>

    @Query("SELECT * FROM trips")
    abstract fun getTripsPaged(): DataSource.Factory<Int, TripEntity>

    @Query("""
        SELECT
            trips.id,
            trips.origin_location_id,
            trips.destination_location_id,
            trips.departure_time,
            trips.arrival_time,
            orig_loc.id as orig_id,
            orig_loc.name as orig_name,
            dst_loc.id as dst_id,
            dst_loc.name as dst_name
        FROM trips
        JOIN locations AS orig_loc ON trips.origin_location_id = orig_loc.id
        JOIN locations AS dst_loc ON trips.destination_location_id = dst_loc.id
        WHERE trips.id = :id
        LIMIT 1
    """)
    abstract fun getFullTrip(id: Long): FullTrip
}


data class FullTrip(
    @Embedded
    val trip: TripEntity,
    @Embedded(prefix = "orig_")
    val origin: LocationEntity,
    @Embedded(prefix = "dst_")
    val destination: LocationEntity
)
//
//"""SELECT *
//FROM sites
//JOIN address ON sites.site_addressid = address.address_id
//JOIN owners ON sites.site_ownerid = owners.owner_id
//JOIN address ON owners.owner_addressid = address.address_id
//WHERE sites.site_id = :siteid"""