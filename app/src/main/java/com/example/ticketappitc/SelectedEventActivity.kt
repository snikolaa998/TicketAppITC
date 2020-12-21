package com.example.ticketappitc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketappitc.adapters.SelectedEventAdapter
import com.example.ticketappitc.api.RetrofitInstance
import com.example.ticketappitc.models.EventDetail
import com.example.ticketappitc.models.MakeReservation
import com.example.ticketappitc.repository.Repository
import com.example.ticketappitc.viewModel.SelectedViewModel
import com.example.ticketappitc.viewModel.SelectedViewModelFactory
import kotlinx.coroutines.CoroutineScope

class SelectedEventActivity : AppCompatActivity() {
    private lateinit var selectedViewModel: SelectedViewModel
    private lateinit var selectedRecycler: RecyclerView
    lateinit var eventDetail: EventDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleceted_event)
        val repository = Repository()
        val btnRemoveReservation = findViewById<Button>(R.id.btn_remove_reservation)
        val btnMakeReservation = findViewById<Button>(R.id.btn_make_reservation)
        val btnMap = findViewById<Button>(R.id.btn_map)

        val etTicket = findViewById<EditText>(R.id.et_number_tickets)
        selectedRecycler = findViewById(R.id.selectedRecycler)
        val selectedViewModelFactory = SelectedViewModelFactory(repository)
        selectedViewModel = ViewModelProvider(this, selectedViewModelFactory).get(SelectedViewModel::class.java)
        selectedViewModel.getSelectedEvent()
        selectedViewModel.selectedEvent.observe(this, Observer {
            eventDetail = it
            val adapter = SelectedEventAdapter(this, it)
            selectedRecycler.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@SelectedEventActivity)
            }
        })

        btnMakeReservation.setOnClickListener {
            if (etTicket.text.toString().isNotEmpty()) {
                val ticketReservation = MakeReservation(etTicket.text.toString().toInt())
                AppData.tickets = ticketReservation
                if (eventDetail.available_tickets < etTicket.text.toString().toInt()) {
                    Toast.makeText(this, "Nema dovoljno mesta", Toast.LENGTH_SHORT).show()
                } else {
                    selectedViewModel.makeReservation()
                    selectedViewModel.getSelectedEvent()
                    selectedViewModel.selectedEvent.observe(this, Observer {
                        val adapter = SelectedEventAdapter(this, it)
                        selectedRecycler.apply {
                            this.adapter = adapter
                            layoutManager = LinearLayoutManager(this@SelectedEventActivity)
                        }
                    })
                }
            } else {
                Toast.makeText(this, "Popunite polje za broj karata", Toast.LENGTH_SHORT).show()
            }
        }

        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        btnRemoveReservation.setOnClickListener {
            selectedViewModel.removeReservation()
            selectedViewModel.getSelectedEvent()
            selectedViewModel.selectedEvent.observe(this, Observer {
                val adapter = SelectedEventAdapter(this, it)
                selectedRecycler.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(this@SelectedEventActivity)
                }
            })
            selectedViewModel.removeMessage.observe(this, Observer {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            })
        }
    }
}