package com.jburch.flightroutescalculator.usecases.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FlightsViewModel: ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

}