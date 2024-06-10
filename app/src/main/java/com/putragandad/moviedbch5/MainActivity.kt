package com.putragandad.moviedbch5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.putragandad.moviedbch5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.NavHostFragment)
        val bottomNavbar = binding.bottomNavigation

        bottomNavbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.splashFragment, R.id.loginFragment, R.id.registerFragment, R.id.movieDetailFragment, R.id.profileEditFragment -> {
                    bottomNavbar.visibility = View.GONE
                }
                else -> {
                    bottomNavbar.visibility = View.VISIBLE
                }
            }
        }
    }
}