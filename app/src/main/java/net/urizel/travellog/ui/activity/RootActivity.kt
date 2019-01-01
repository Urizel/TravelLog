package net.urizel.travellog.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import net.urizel.travellog.R

class RootActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        this.navController = findNavController(R.id.fragment_nav_host)
        this.bottomNavigationView = findViewById(R.id.widget_navigation_view)
        this.bottomNavigationView.setupWithNavController(navController)
    }
}
