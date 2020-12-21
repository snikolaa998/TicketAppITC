package com.example.ticketappitc.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketappitc.AppData
import com.example.ticketappitc.R
import com.example.ticketappitc.SelectedEventActivity
import com.example.ticketappitc.models.Events

class EventsHolder(view: View) : RecyclerView.ViewHolder(view) {
    val teamOne: TextView = view.findViewById(R.id.events_item_team_one)
    val teamTwo: TextView = view.findViewById(R.id.events_item_team_two)
    val sport: TextView = view.findViewById(R.id.events_item_sport)
    val address: TextView = view.findViewById(R.id.events_item_location)
    val start: TextView = view.findViewById(R.id.events_item_start)
    val end: TextView = view.findViewById(R.id.events_item_end)
    val itemConstraint: ConstraintLayout = view.findViewById(R.id.events_constraint)
}

class EventsAdapter(private val context: Context, private val eventsList: ArrayList<Events>) : RecyclerView.Adapter<EventsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.events_item, parent, false)
        return EventsHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        val event = eventsList[position]
        with(holder) {
            teamOne.text = event.teams[0].name
            teamTwo.text = event.teams[1].name
            sport.text = event.sport
            address.text = event.address
            start.text = event.start
            end.text = event.end
            itemConstraint.setOnClickListener {
                val intent = Intent(context, SelectedEventActivity::class.java)
                AppData.event = event
                context.startActivity(intent)
            }
        }
    }
}