package com.example.ticketappitc.viewModel


import androidx.lifecycle.*
import com.example.ticketappitc.AppData
import com.example.ticketappitc.models.Events
import com.example.ticketappitc.repository.Repository
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: Repository) : ViewModel() {
    val events: MutableLiveData<ArrayList<Events>> = MutableLiveData()
    private val userId = AppData.userCredential?.id
    fun getAllEvents() {
        viewModelScope.launch {
            val response = repository.getAllEvents(userId.toString())
            events.value = response
        }
    }
}

class EventsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventsViewModel(repository) as T
    }
}