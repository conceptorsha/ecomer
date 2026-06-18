package com.example.authentication.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.authentication.viewModel.AuthViewModel

@Composable
fun SignUpScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var passwordErrorMessage by remember { mutableStateOf("") }

    val signupResponse by viewModel.signupResponse.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(signupResponse) {
        if (signupResponse?.token != null) {
            navController.navigate("login")
        }
    }

    LaunchedEffect(error) {
        error?.let {
            emailError = true
            emailErrorMessage = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SIGN UP",
            modifier = Modifier.padding(20.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Green
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            isError = emailError,
            supportingText = {
                if (emailError) Text(emailErrorMessage)
            }
        )
        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {
                password = it
                passwordError = false
            },
            label = { Text("Password") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            isError = passwordError,
            supportingText = {
                if (passwordError) Text(passwordErrorMessage)
            }
        )
        Button(
            onClick = {
                if (email.isEmpty()) {
                    emailError = true
                    emailErrorMessage = "Enter email"
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = true
                    emailErrorMessage = "Enter a valid email"
                } else if (password.isEmpty()) {
                    passwordError = true
                    passwordErrorMessage = "Enter password"
                } else {
                    emailError = false
                    passwordError = false
                    viewModel.signup(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = CircleShape
        ) {
            Text("SIGN UP")
        }
    }
}