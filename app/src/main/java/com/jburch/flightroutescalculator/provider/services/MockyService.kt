package com.jburch.flightroutescalculator.provider.services

import android.content.Context
import android.util.Log
import com.jburch.flightroutescalculator.core.RetrofitHelper
import com.jburch.flightroutescalculator.model.domain.Airline
import com.jburch.flightroutescalculator.model.domain.AirlinesResponse
import com.jburch.flightroutescalculator.model.domain.Flight
import com.jburch.flightroutescalculator.model.domain.FlightsResponse
import com.jburch.flightroutescalculator.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

object MockyService {

    private enum class MockyServiceAPI {

        MOCKY;

        fun baseUrl(): String {

            return when (this) {
                MOCKY -> Constants.MOCKY_DATA_URI
            }
        }
    }

    private interface MockyAPIService {

        @GET("987c7d08-372b-4d28-ad8b-554d87c669f4")
        fun flights(): Call<FlightsResponse>

        @GET("")
        fun airlines(): Call<AirlinesResponse>

    }

    fun getFlights(context: Context, success: (flights: List<Flight>) -> Unit, failure: () -> Unit, retry: Boolean = false) {
        val retrofit = RetrofitHelper.getRetrofit(MockyServiceAPI.MOCKY.baseUrl())
        val service = retrofit.create(MockyAPIService::class.java)
        val headers: Map<String, String> = mapOf("Accept" to "application/json")
        service.flights().enqueue(object: Callback<FlightsResponse> {
            override fun onResponse(
                call: Call<FlightsResponse>,
                response: Response<FlightsResponse>
            ) {
                if (response.isSuccessful) {
                    val flights = response.body()?.flights?.toMutableList()
                    if (flights != null) {
                        success(flights)
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<FlightsResponse>, t: Throwable) {
                Log.e("getFlights onFailure", "Error searching flights: ${t.localizedMessage}", t);
                failure()
            }

        })
    }

    fun getAirlines(context: Context, success: (flights: List<Airline>) -> Unit, failure: () -> Unit, retry: Boolean = false) {
        val retrofit = RetrofitHelper.getRetrofit(MockyServiceAPI.MOCKY.baseUrl())
        val service = retrofit.create(MockyAPIService::class.java)
        val headers: Map<String, String> = mapOf("Accept" to "application/json")
        service.airlines().enqueue(object: Callback<AirlinesResponse> {
            override fun onResponse(
                call: Call<AirlinesResponse>,
                response: Response<AirlinesResponse>
            ) {
                if (response.isSuccessful) {
                    val airlines = response.body()?.airlines?.toMutableList()
                    if (airlines != null) {
                        success(airlines)
                    } else {
                        failure()
                    }
                } else {
                    failure()
                }
            }

            override fun onFailure(call: Call<AirlinesResponse>, t: Throwable) {
                Log.e("getAirlines onFailure", "Error searching airlines: ${t.localizedMessage}", t);
                failure()
            }

        })
    }
}