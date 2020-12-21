package com.example.ticketappitc.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ticketappitc.AppData
import com.example.ticketappitc.models.UserCredential
import com.example.ticketappitc.repository.Repository
import kotlinx.coroutines.launch

class AuthenticateViewModel(private val repository: Repository) : ViewModel() {
    val authResponse: MutableLiveData<UserCredential> = MutableLiveData()
    val userEmail = AppData.user!!.email
    fun authUser() {
        viewModelScope.launch {
            val response = repository.authenticateUser(userEmail)
            authResponse.value = response
        }
    }
}

class AuthenticateViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticateViewModel(repository) as T
    }
}