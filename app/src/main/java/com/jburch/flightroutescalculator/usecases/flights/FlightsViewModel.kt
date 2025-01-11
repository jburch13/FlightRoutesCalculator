package com.jburch.flightroutescalculator.usecases.flights

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jburch.flightroutescalculator.model.domain.Flight
import com.jburch.flightroutescalculator.provider.services.MockyService

class FlightsViewModel: ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    var flights: List<Flight> = arrayListOf()
        private set

    var genericError: Boolean = false
        private set

    fun load() {
        loading.postValue(false)
    }

    fun getFlights(context: Context) {
        loading.postValue(true)

        MockyService.getFlights(context, { flightsRes ->
            flights = flightsRes
            load()
        }, {
            onGetFlightsError()
        })
    }

    private fun onGetFlightsError() {
        flights = arrayListOf()
        genericError = true
        load()
    }

}