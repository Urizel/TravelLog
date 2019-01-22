package net.urizel.travellog.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.urizel.travellog.R
import net.urizel.travellog.persistence.database.AppDatabase
import net.urizel.travellog.persistence.entity.LocationEntity
import net.urizel.travellog.persistence.entity.TripEntity
import org.threeten.bp.ZonedDateTime
import kotlin.coroutines.CoroutineContext

class TripListFragment : Fragment() {

    private lateinit var addTripFab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.addTripFab = view.findViewById(R.id.fab_add_trip)

        this.addTripFab.setOnClickListener {
            daoTest()
        }

//        val clickListener = Navigation.createNavigateOnClickListener(R.id.navigation_add_trip_fragment)
//        this.addTripFab.setOnClickListener(clickListener)
    }

    private fun daoTest() {

        val context = requireNotNull(this@TripListFragment.context) {
            "context cannot be null"
        }

        // FIXME is terrible - no exception handling
        GlobalScope.launch(Dispatchers.IO) {

            val db = AppDatabase.instance(context)
            val locationDao = db.locationDao()

            val origin = LocationEntity(name = "Origin")
            val originId = locationDao.addLocation(origin)
            Log.e(TAG, "originId: $originId")

            val destination = LocationEntity(name = "Destination")
            val destinationId = locationDao.addLocation(destination)
            Log.e(TAG, "destinationId: $destinationId")

            val trip = TripEntity(
                originLocationId = originId,
                destinationLocationId = destinationId,
                departureTime = ZonedDateTime.now(),
                arrivalTime = ZonedDateTime.now().plusHours(2)
            )
            Log.e(TAG, "Trip: $trip")

            val tripDao = db.tripDao()
            val tripId = tripDao.addTrip(trip)

            val savedTrip = tripDao.getFullTrip(tripId)
            Log.e(TAG, "Saved trip: $savedTrip")
        }
    }

    companion object {
        private val TAG = TripListFragment::class.java.simpleName
    }
}
