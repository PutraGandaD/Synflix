package com.putragandad.synflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.putragandad.synflix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.NavHostFragment)
        val bottomNavbar = binding.bottomNavigation

        bottomNavbar.setupWithNavController(navController)

        buildDialog()

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

    private fun buildDialog() {
        val message = "Product Flavor = ${BuildConfig.FLAVOR} \nBuild Type = ${BuildConfig.BUILD_TYPE}"

        Log.d("BuildConfig", "BUILD_FLAVOR: ${BuildConfig.FLAVOR}, BUILD_TYPE: ${BuildConfig.BUILD_TYPE}")

        MaterialAlertDialogBuilder(this)
            .setTitle("About Synflix Build")
            .setMessage(message)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}