package com.example.ticketappitc.repository

import android.util.Log
import com.example.ticketappitc.api.RetrofitInstance
import com.example.ticketappitc.models.*
import retrofit2.Response

class Repository {
    suspend fun authenticateUser(userEmail: String) : UserCredential {
        val user = AuthenticateCredential(userEmail)
        return RetrofitInstance.api.authenticateUser(user)
    }

    suspend fun getAllEvents(userId: String) : ArrayList<Events> {
        Log.d("Repo", userId)
        return RetrofitInstance.api.getAllEvent(userId)
    }

    suspend fun getSelectedEvent(userId: String, eventId: String) : EventDetail {
        return RetrofitInstance.api.getSelectedEvent(userId, eventId)
    }

    suspend fun removeReservation(userId: String, eventId: String) : Message {
        return RetrofitInstance.api.deleteReservation(userId, eventId)
    }

    suspend fun makeReservation(tickets: MakeReservation, userId: String, eventId: String) {
        RetrofitInstance.api.makeReservation(tickets, userId, eventId)
    }
}