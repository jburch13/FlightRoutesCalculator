package com.jburch.flightroutescalculator.model.domain

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class FlightsResponse(val flights: List<Flight>? = null)

data class Flight(
    @SerializedName("id") val id: String? = null,
    @SerializedName("airline") val airline: String? = null,
    @SerializedName("departure_airport") val departureAirport: String? = null,
    @SerializedName("arrival_airport") val arrivalAirport: String? = null,
    @SerializedName("price") val price: Double? = null
) {

    // Gson

    companion object {
        fun toJson(flight: Flight): String {
            return GsonBuilder().create().toJson(flight)
        }

        fun fromJson(json: String): Flight {
            return GsonBuilder().create().fromJson(json, Flight::class.java)
        }
    }
}
