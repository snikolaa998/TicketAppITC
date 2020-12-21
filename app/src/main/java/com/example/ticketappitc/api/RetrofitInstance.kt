package com.example.ticketappitc.api

import com.example.ticketappitc.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: UtilitiesApi by lazy {
        retrofit.create(UtilitiesApi::class.java)
    }
}