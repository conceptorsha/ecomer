package com.example.authentication.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController : NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError  by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordError by remember {mutableStateOf(false)}
    var passwordErrorMessage by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "welcome",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        TextField(
            value = email,
            onValueChange = {
                email = it


            },
            label = {Text("email")},
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(emailErrorMessage)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true


        )
        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            label = {Text("enterpassword")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = passwordError,
            supportingText = {
                if (passwordError) {
                    Text(passwordErrorMessage)
                }
            }


        )
        Button(onClick = {
            if (email.isEmpty()){
                emailError = true
                emailErrorMessage= "enter valid email"
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = true
            emailErrorMessage = "Not a valid email address"
        }else if (password.isEmpty()){
            passwordError = true
                passwordErrorMessage = "please enter password"
        }else {
                emailError = false
                passwordError = false
                navController.navigate("home")
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),


            ) {
            Text(text = "Login", fontSize = 16.sp)
        }
    }
}