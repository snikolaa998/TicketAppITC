package com.example.ticketappitc.api

import com.example.ticketappitc.models.*
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UtilitiesApi {
    @POST("auth")
    suspend fun authenticateUser(@Body data: AuthenticateCredential) : UserCredential

    @POST("users/{id}/events/{event_id}/unbook")
    suspend fun deleteReservation(@Path("id") id: String, @Path("event_id") event_id: String) : Message

    @POST("users/{id}/events/{event_id}/book")
    suspend fun makeReservation(@Body tickets: MakeReservation, @Path("id") id: String, @Path("event_id") event_id: String)

    @GET("users/{id}/events")
    suspend fun getAllEvent(@Path("id") id: String) : ArrayList<Events>

    @GET("users/{id}/events/{event_id}")
    suspend fun getSelectedEvent(@Path("id") id: String, @Path("event_id") event_id: String) : EventDetail
}