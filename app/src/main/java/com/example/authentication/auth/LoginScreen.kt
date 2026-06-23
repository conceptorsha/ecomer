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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.authentication.viewModel.AuthViewModel

@Composable
fun LoginScreen(navController : NavController, viewModel: AuthViewModel = viewModel() ){
    var password by remember { mutableStateOf("") }
    var username by remember {mutableStateOf("")}

    // error logic variables

    var passwordError by remember {mutableStateOf(false)}
    var passwordErrorMessage by remember { mutableStateOf("") }
    var usernameError by remember {mutableStateOf(false)}
    var usernameErrorMessage  by remember {mutableStateOf("")}

    //viewmodel variable
    val loginResponse by viewModel.loginResponse.observeAsState()
    val error by viewModel.error.observeAsState()

    LaunchedEffect(loginResponse) {
        if (loginResponse?.token != null) {
            navController.navigate("home")
        }
    }

    LaunchedEffect(error) {
        error?.let {
            usernameError = true
            usernameErrorMessage = it
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
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
        Button(onClick = {
            if (username.isEmpty()){
                usernameError = true
                usernameErrorMessage= "enter valid email"


        }else if (password.isEmpty()){
            passwordError = true
                passwordErrorMessage = "please enter password"
        }else {
                usernameError = false
                passwordError = false
                viewModel.login(username, password)
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