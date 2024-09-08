package com.example.gabinsbarv2

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
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

        // Observer les changements dans le panier
        PanierManager.panierSizeLiveData.observe(this, Observer { newSize ->
            Log.d("MainActivity", "Taille du panier reçue : $newSize")
            panierBadge.number = newSize // Mettre à jour le badge avec la nouvelle taille du panier
        })

        // Initialiser le badge avec la taille actuelle du panier au démarrage
        val currentSize = PanierManager.getPanierSize()
        Log.d("MainActivity", "Taille initiale du panier : $currentSize")
        panierBadge.number = currentSize

        setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
