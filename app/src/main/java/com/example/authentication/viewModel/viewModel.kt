package com.example.authentication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.Respiratory.AuthRepository
import com.example.authentication.model.LoginResponse
import com.example.authentication.model.SignupResponse
import com.example.authentication.model.ProductsResponse
import com.example.authentication.remote.RetrofitInstance
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    init {
        getProducts()
    }
    private val repository = AuthRepository()

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _productsResponse =
        MutableLiveData<List<ProductsResponse>>()

    val productsResponse: LiveData<List<ProductsResponse>>
            = _productsResponse

    private val _signupResponse = MutableLiveData<SignupResponse?>()
    val signupResponse: LiveData<SignupResponse?> = _signupResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {

        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                Log.d("AUTH", "Attempting login with username: $username")
                val response = repository.login(username, password)
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

    fun signup(
        email: String,
        username: String,
        password: String,
        name: String,
        address: String,
        phone: String
    ) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                Log.d("AUTH", "Attempting signup with email: $username")
                val response = repository.signup(email, username, password, name, address, phone)
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

    fun getProducts() {
        _isLoading.postValue(true)

        viewModelScope.launch {
            try {
                val response = repository.getProducts()

                if (response.isSuccessful) {
                    response.body()?.let { products ->
                        _productsResponse.postValue(products)
                    } ?: run {
                        _error.postValue("No products found")
                    }
                } else {
                    _error.postValue("Failed: ${response.errorBody()?.string()}")
                }

            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}

