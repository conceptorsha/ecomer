package com.example.authentication.auth

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(navController : NavController){
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullNameError  by remember{mutableStateOf(true)}
    var fullNameErrorMessage by remember { mutableStateOf("") }
    var emailErrorMessage by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(true) }
    var passwordError   by remember { mutableStateOf(true) }
    var passwordErrorMessage by remember { mutableStateOf("") }
    var phoneNumberError   by remember { mutableStateOf(true) }
    var phoneNumberErrorMessage by remember { mutableStateOf("") }
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
            value = fullName,
            onValueChange = {fullName = it},
            label = {Text("FullName")},
            modifier = Modifier
                .padding( 16.dp)
                .fillMaxWidth(),
            shape = CircleShape,

            supportingText = {
                if (fullNameError) {
                   Text (fullNameErrorMessage)
                }
            }
        )
      TextField(
            value = email,
            onValueChange = {email = it
                            },
            label = {Text("email")},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = CircleShape,


            supportingText =  {
                if (emailError){
                    (emailErrorMessage) }
            }
        )
        TextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = {Text("PhoneNumber")},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            supportingText =  {if (phoneNumberError){
                (phoneNumberErrorMessage)
            } }
        )
        TextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {password = it},
            label = {Text("password")},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = CircleShape,

            supportingText =  {if (passwordError) {
                (passwordErrorMessage)
            } }
        )
        Button(
            onClick = {
                if (fullName.isEmpty()){
                    fullNameError = true
                    fullNameErrorMessage = "enter full name"
                }else if (email.isEmpty()){
                    emailError = true
                        emailErrorMessage = "enter email"
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailError = true
                    emailErrorMessage = "enter a valid email"
                    }else if (phoneNumber.isEmpty()){
                        phoneNumberError = true
                        phoneNumberErrorMessage = "enter number"
                    }else if (password.isEmpty()){
                        passwordError = true
                        passwordErrorMessage = "enter password"
                    }else {
                    fullNameError = false
                    emailError = false
                    phoneNumberError = false
                    passwordError = false

                    navController.navigate("Login" )
                    }


            },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            shape = CircleShape,



        ) {
            Text("SIGN UP")
        }

    }
}