package net.urizel.travellog.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class TravelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }
}