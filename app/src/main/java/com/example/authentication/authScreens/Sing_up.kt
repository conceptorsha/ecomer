package com.example.authentication.authScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
    var username by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("")}
    var address by remember {mutableStateOf("")}
    var phone by remember {mutableStateOf("")}

    // error logic variables
    var emailError  by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordError by remember {mutableStateOf(false)}
    var passwordErrorMessage by remember { mutableStateOf("") }
    var usernameError by remember {mutableStateOf(false)}
    var usernameErrorMessage  by remember {mutableStateOf("")}
    var nameError by remember { mutableStateOf(false) }
    var nameErrorMessage by remember { mutableStateOf("") }
    var addressError by remember { mutableStateOf(false) }
    var addressErrorMessage by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }
    var phoneErrorMessage by remember { mutableStateOf("") }


    val signupResponse by viewModel.signupResponse.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(signupResponse) {
        if (signupResponse?.id != null) {
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
            onValueChange = { email = it
                emailError = false},
            label = {Text("email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                capitalization = KeyboardCapitalization.None
            ),
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
            value =username,
            onValueChange = {username = it
                usernameError =  false},
            label = {Text("username")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = usernameError,
            supportingText = {if (usernameError){
                Text (usernameErrorMessage)
            }},
            singleLine = true
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            label = {Text("enter password")},
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
        TextField(
            value = name,
            onValueChange = {name = it},
            label = {Text("full name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = nameError,
            supportingText = {
                if (nameError){
                    Text(nameErrorMessage)
                }
            }

        )
        TextField(
            value = address,
            onValueChange = {address = it
                addressError = false},
            label = {Text("address")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = addressError,
            supportingText ={
                if (addressError){Text(addressErrorMessage)}
            }
        )

        TextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = false
            },
            label = { Text("phone number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isError = phoneError,
            supportingText = {
                if (phoneError){
                    Text(phoneErrorMessage)}

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
                } else if (username.isEmpty()) {
                    usernameError = true
                    usernameErrorMessage = "Enter username"
                }
                else if (password.isEmpty()) {
                    passwordError = true
                    passwordErrorMessage = "Enter password"
                }else if (name.isEmpty()) {
                    nameError = true
                    nameErrorMessage = "Enter full name"
                }else if (address.isEmpty()) {
                    addressError = true
                    addressErrorMessage = "Enter address"
                }else if (phone.isEmpty()) {
                    phoneError = true
                    phoneErrorMessage = "Enter phone number"
            }else{
                    emailError = false
                    passwordError = false
                    nameError = false
                    addressError = false
                    phoneError = false
                    viewModel.signup(email,username, password,name,address,phone)
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