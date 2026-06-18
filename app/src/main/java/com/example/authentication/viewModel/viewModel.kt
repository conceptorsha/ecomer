package com.example.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.Respiratory.AuthRepository
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.SignupResponse
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _signupResponse = MutableLiveData<SignupResponse?>()
    val signupResponse: LiveData<SignupResponse?> = _signupResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                _loginResponse.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.signup(email, password)
                _signupResponse.postValue(response)
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }
}