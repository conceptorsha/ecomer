
package com.example.authentication


import android.os.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.example.authentication.auth.LoginScreen
import com.example.authentication.auth.SignUpScreen
import com.example.authentication.ui.theme.AuthenticationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthenticationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,startDestination= "sign up"){
                    composable(route = "sign up"){
                        SignUpScreen(navController = navController)
                    }
                    composable(route ="login") {
                        LoginScreen(navController = navController)
                    }
                    composable(route= "home" ){
                      HomeScreen()
                    }
                }


                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AuthenticationTheme {
        val navController = rememberNavController()
        LoginScreen(navController)    }
}