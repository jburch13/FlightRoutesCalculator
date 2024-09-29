package com.jburch.flightroutescalculator.usecases.flights

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.jburch.flightroutescalculator.databinding.ActivityFlightsBinding

class FlightsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlightsBinding

    private lateinit var viewModel: FlightsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFlightsBinding.inflate(layoutInflater)

        // Content
        setContentView(binding.root)

        // View Model
        viewModel = ViewModelProvider(this)[FlightsViewModel::class.java]

        setup()
        data()
    }

    private fun setup() {

        binding.tabsFlights.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Toast.makeText(applicationContext, "${tab.text} seleccionat", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Toast.makeText(applicationContext, "${tab.text} reseleccionat", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Toast.makeText(applicationContext, "${tab.text} deseleccionat", Toast.LENGTH_SHORT).show()
                }
            }

        })

        if (VERSION.SDK_INT >= VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

    }

    private fun data() {

    }
}
