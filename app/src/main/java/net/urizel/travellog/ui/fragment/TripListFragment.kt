package net.urizel.travellog.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.urizel.travellog.R

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
        val clickListener = Navigation.createNavigateOnClickListener(R.id.navigation_add_trip_fragment)

        this.addTripFab.setOnClickListener(clickListener)
    }
}
