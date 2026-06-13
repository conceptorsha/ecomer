package com.example.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.api.RetrofitInstance
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    init {
        createUser()
    }

    fun createUser() {

        viewModelScope.launch {

            try {

             //   val response = RetrofitInstance.api.createUser()

             //   _user.value = response.username

            } catch (e: Exception) {

                _user.value = "Error: ${e.message}"

            }
        }
    }
}