package com.example.ticketappitc.models

data class EventDetail(
    val id: Int,
    val address: String,
    val lat: Double,
    val long: Double,
    val start: String,
    val end: String,
    val sport: String,
    val price: Int,
    val tickets: Int,
    val available_tickets: Int,
    val teams: ArrayList<Teams>
)

data class Teams(val name: String)

data class Events(
    val id: Int,
    val address: String,
    val lat: Double,
    val long: Double,
    val start: String,
    val end: String,
    val sport: String,
    val teams: ArrayList<Teams>
)