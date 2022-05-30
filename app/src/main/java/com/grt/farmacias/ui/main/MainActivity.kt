package com.grt.farmacias.ui.main

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.grt.farmacias.R
import com.grt.farmacias.common.BaseActivity
import com.grt.farmacias.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_deGuardia, R.id.navigation_listadoFarmacias, R.id.navigation_deInteres
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initListeners()
    }

    fun showLoading() {
        binding.flMainLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.flMainLoading.visibility = View.GONE
    }

    private fun initListeners() {

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}