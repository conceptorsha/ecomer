package com.example.authentication.viewModel

import android.util.Log
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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                Log.d("AUTH", "Attempting login with email: $email")
                val response = repository.login(email, password)
                Log.d("AUTH", "Login code: ${response.code()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.error != null) {
                        _error.postValue(body.error)
                    } else {
                        _loginResponse.postValue(body)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AUTH", "Login error body: $errorBody")
                    _error.postValue("Login failed: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("AUTH", "Login exception: ${e.message}")
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                Log.d("AUTH", "Attempting signup with email: $email")
                val response = repository.signup(email, password)
                Log.d("AUTH", "Signup code: ${response.code()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.error != null) {
                        _error.postValue(body.error)
                    } else {
                        _signupResponse.postValue(body)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("AUTH", "Signup error body: $errorBody")
                    _error.postValue("Signup failed: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("AUTH", "Signup exception: ${e.message}")
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}