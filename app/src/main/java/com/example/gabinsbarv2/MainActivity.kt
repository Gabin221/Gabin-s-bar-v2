package com.example.gabinsbarv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gabinsbarv2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.softsFragment,
                R.id.alcoolFragment,
                R.id.panierFragment,
                R.id.compteFragment
            )
        )

        val navBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val panierBadge = navBar.getOrCreateBadge(R.id.panierFragment)

        PanierManager.panierSizeLiveData.observe(this) { newSize ->
            panierBadge.number = newSize
        }

        val currentSize = PanierManager.getPanierSize()
        panierBadge.number = currentSize

        setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
