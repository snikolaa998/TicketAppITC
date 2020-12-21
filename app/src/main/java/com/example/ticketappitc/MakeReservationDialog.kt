package com.example.ticketappitc

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class MakeReservationDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.make_reservation_dialog)
        val makeButton = findViewById<Button>(R.id.dialog_button_make)
        val cancelButton = findViewById<Button>(R.id.dialog_button_cancel)
        val numberTickets = findViewById<EditText>(R.id.dialog_number_tickets)
        makeButton.setOnClickListener {
            Toast.makeText(context, "Make akcija", Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener {
            Toast.makeText(context, "Del akcija", Toast.LENGTH_SHORT).show()
            dismiss()
        }

    }
}