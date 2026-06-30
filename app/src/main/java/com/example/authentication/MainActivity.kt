package com.example.authentication

import android.os.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.authentication.authScreens.HomeScreen
import com.example.authentication.authScreens.LoginScreen
import com.example.authentication.authScreens.ProductDetailScreen
import com.example.authentication.authScreens.SignUpScreen
import com.example.authentication.ui.theme.AuthenticationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthenticationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = "signup") {
                        SignUpScreen(navController = navController)
                    }
                    composable(route = "login") {
                        LoginScreen(navController = navController)
                    }
                    composable(route = "home") {
                        HomeScreen(navController = navController)
                    }
                    composable(
                        route = "productDetail/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        ProductDetailScreen(navController = navController, productId = productId)
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
        LoginScreen(navController)
    }
}