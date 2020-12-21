package com.example.ticketappitc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private  var long: Double? = null
    private  var lat: Double? = null
    private lateinit var map: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        long = AppData.event?.long
        lat = AppData.event?.lat
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        val position = LatLng(lat!!, long!!)
        val cameraPosition = CameraPosition.Builder()
            .target(position)
            .zoom(50f)
            .bearing(70f)
            .tilt(25f)
            .build()
        map.addMarker(MarkerOptions()
            .position(position)
            .title("Your Event")
            .snippet("Your selected event!"))

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}