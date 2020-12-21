package com.example.ticketappitc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketappitc.adapters.EventsAdapter
import com.example.ticketappitc.repository.Repository
import com.example.ticketappitc.viewModel.EventsViewModel
import com.example.ticketappitc.viewModel.EventsViewModelFactory

class EventsActivity : AppCompatActivity() {

    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var eventsViewModel: EventsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        eventsRecyclerView = findViewById(R.id.events_activity_recycler)
        val repository = Repository()
        val eventsViewModelFactory = EventsViewModelFactory(repository)
        eventsViewModel = ViewModelProvider(this, eventsViewModelFactory).get(EventsViewModel::class.java)
        eventsViewModel.getAllEvents()
        eventsViewModel.events.observe(this, Observer {
            val adapter = EventsAdapter(this, it)
            eventsRecyclerView.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(this@EventsActivity)
            }
        })
    }
}