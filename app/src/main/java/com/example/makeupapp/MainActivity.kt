package com.example.makeupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.makeupapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up back navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.NavHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}