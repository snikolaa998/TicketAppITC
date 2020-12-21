package com.example.ticketappitc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketappitc.R
import com.example.ticketappitc.models.EventDetail

class SelectedEventHolder(view: View) : RecyclerView.ViewHolder(view) {
    val teamOne: TextView = view.findViewById(R.id.selected_event_team_one)
    val teamTwo: TextView = view.findViewById(R.id.selected_event_team_two)
    val sport: TextView = view.findViewById(R.id.selected_event_sport)
    val address: TextView = view.findViewById(R.id.selected_event_location)
    val start: TextView = view.findViewById(R.id.selected_event_start)
    val end: TextView = view.findViewById(R.id.selected_event_end)
    val price: TextView = view.findViewById(R.id.selected_event_price)
    val tickets: TextView = view.findViewById(R.id.selected_event_ticket)
}
class SelectedEventAdapter(private val context: Context, private val event: EventDetail) : RecyclerView.Adapter<SelectedEventHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedEventHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.selected_event_item, parent, false)
        return SelectedEventHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: SelectedEventHolder, position: Int) {
        with(holder) {
            teamOne.text = event.teams[0].name
            teamTwo.text = event.teams[1].name
            sport.text = event.sport
            address.text = event.address
            start.text = event.start
            end.text = event.end
            price.text = event.price.toString()
            tickets.text = event.available_tickets.toString()
        }
    }
}