package com.example.ticketappitc

import android.app.Application
import com.example.ticketappitc.models.*

class AppData : Application() {
    companion object {
        var user: AuthenticateCredential? = null
        var userCredential: UserCredential? = null
        var event: Events? = null
        var tickets: MakeReservation? = null
    }
}