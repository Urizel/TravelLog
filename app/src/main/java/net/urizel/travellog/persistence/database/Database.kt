package net.urizel.travellog.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.urizel.travellog.persistence.dao.LocationDao
import net.urizel.travellog.persistence.dao.TripDao
import net.urizel.travellog.persistence.entity.LocationEntity
import net.urizel.travellog.persistence.entity.TripEntity

@Database(
    entities = [LocationEntity::class, TripEntity::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
    abstract fun tripDao(): TripDao

    companion object {
        fun instance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "word_database"
            ).build()
        }
    }

}