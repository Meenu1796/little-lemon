package com.coursera.capstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.coursera.capstone.ui.theme.CapstoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CapstoneTheme {
                // Create the NavController using rememberNavController() delegate.
                // It is lifecycle-aware and survives recompositions.
                val navController = rememberNavController()

                // Hand the navController to MyNavigation, which owns the
                // NavHost and all composable route definitions.
                MyNavigation(navController = navController)
            }
        }
    }
}