package net.urizel.travellog.persistence.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

import net.urizel.travellog.persistence.entity.LocationEntity
import net.urizel.travellog.persistence.entity.TripEntity

@Dao
abstract class LocationDao(db: RoomDatabase) {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addLocation(location: LocationEntity): Long

    @Delete()
    abstract fun deleteLocation(location: LocationEntity)

    @Query("SELECT * FROM locations WHERE id = :id LIMIT 1")
    abstract fun getLocationById(id: Long): LocationEntity

    @Query("SELECT * FROM locations")
    abstract fun getLocations(): List<LocationEntity>

    @Query("SELECT * FROM locations")
    abstract fun getLocationsPaged(): DataSource.Factory<Int, LocationEntity>
}