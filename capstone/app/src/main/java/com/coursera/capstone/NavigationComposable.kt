package com.coursera.capstone

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * MyNavigation manages the entire navigation graph for the Little Lemon app.
 *
 * Start destination logic:
 *  - If user data is found in SharedPreferences → start at [Home]
 *  - Otherwise (first launch / logged out)      → start at [Onboarding]
 *
 * @param navController The [NavHostController] created in [MainActivity].
 */
@Composable
fun MyNavigation(navController: NavHostController) {
    val context = LocalContext.current

    // Evaluate start destination only once, avoiding re-computation on recomposition
    val startDestination = remember {
        val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.contains("first_name")   // key saved during registration
        if (isLoggedIn) Home.route else Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }

        composable(Home.route) {
            Home(navController = navController)
        }

        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}