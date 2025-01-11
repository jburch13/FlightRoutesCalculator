package com.jburch.flightroutescalculator.model.domain

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName

data class AirlinesResponse(val airlines: List<Airline>? = null)

data class Airline(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null
) {

    // Gson

    companion object {
        fun toJson(airline: Airline): String {
            return GsonBuilder().create().toJson(airline)
        }

        fun fromJson(json: String): Airline {
            return GsonBuilder().create().fromJson(json, Airline::class.java)
        }
    }
}
