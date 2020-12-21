package com.example.ticketappitc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ticketappitc.models.AuthenticateCredential
import com.example.ticketappitc.models.UserCredential
import com.example.ticketappitc.repository.Repository
import com.example.ticketappitc.viewModel.AuthenticateViewModel
import com.example.ticketappitc.viewModel.AuthenticateViewModelFactory

class AuthenticateActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthenticateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)
        Log.d("AuthActivity", "onCreateAuth")
        val repository = Repository()
        val viewModelFactory = AuthenticateViewModelFactory(repository)
        val authButton: Button = findViewById(R.id.activity_auth_btn)
        val authEmail: EditText = findViewById(R.id.activity_auth_et_email)

        authButton.setOnClickListener {
            if (authEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "Unesite email!", Toast.LENGTH_SHORT).show()
            } else {
                AppData.user = AuthenticateCredential(authEmail.text.toString())
                viewModel = ViewModelProvider(this, viewModelFactory).get(AuthenticateViewModel::class.java)
                viewModel.authUser()
                viewModel.authResponse.observe(this, Observer {
                    AppData.userCredential = it
                    Log.d("TEST", it.email)
                    val intent = Intent(this, EventsActivity::class.java)
                    startActivity(intent)
                })

            }
        }
    }
}