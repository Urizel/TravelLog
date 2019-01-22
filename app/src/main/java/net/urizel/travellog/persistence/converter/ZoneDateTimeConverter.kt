package net.urizel.travellog.persistence.converter

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZoneDateTimeConverter {

    @TypeConverter
    fun writeZonedDateTime(zonedDateTime: ZonedDateTime?): String? {
        zonedDateTime ?: return null
        return formatter.format(zonedDateTime)
    }

    @TypeConverter
    fun readZonedDateTime(serializedTime: String?): ZonedDateTime? {
        serializedTime ?: return null
        return ZonedDateTime.parse(serializedTime, formatter)
    }

    private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
}