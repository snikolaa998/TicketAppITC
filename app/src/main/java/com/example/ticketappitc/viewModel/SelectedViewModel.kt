package com.example.ticketappitc.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ticketappitc.AppData
import com.example.ticketappitc.models.EventDetail
import com.example.ticketappitc.models.MakeReservation
import com.example.ticketappitc.models.Message
import com.example.ticketappitc.repository.Repository
import kotlinx.coroutines.launch


class SelectedViewModel(private val repository: Repository) : ViewModel() {
    val selectedEvent: MutableLiveData<EventDetail> = MutableLiveData()
    val userId = AppData.userCredential?.id
    val eventId = AppData.event?.id
    val removeMessage: MutableLiveData<Message> = MutableLiveData()
    fun getSelectedEvent() {
        viewModelScope.launch {
            val response = repository.getSelectedEvent(userId.toString(), eventId.toString())
            selectedEvent.value = response
        }
    }
    fun removeReservation() {
        viewModelScope.launch {
            val response = repository.removeReservation(userId.toString(), eventId.toString())
            removeMessage.value = response
            repository.getSelectedEvent(userId.toString(), eventId.toString())
        }
    }
    fun makeReservation() {
        viewModelScope.launch {
            val response = repository.makeReservation(AppData.tickets!!, userId.toString(), eventId.toString())
            repository.getSelectedEvent(userId.toString(), eventId.toString())
        }
    }

}

class SelectedViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelectedViewModel(repository) as T
    }
}