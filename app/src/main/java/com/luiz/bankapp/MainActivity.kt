// MainActivity.kt
package com.luiz.bankapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.luiz.bankapp.view.HomeScreen
import com.luiz.bankapp.view.LoginScreen
//github

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = rememberNavController()

            NavHost(navController = nav, startDestination = "login") {
                composable("login") {
                    LoginScreen { nav.navigate("home") { popUpTo("login") { inclusive = true } } }
                }
                composable("home") { HomeScreen() }
            }
        }
    }
}
