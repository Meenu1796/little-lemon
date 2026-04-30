package com.coursera.capstone

import android.content.Context
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var statusMessage by remember { mutableStateOf("") }
    var isSuccess     by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.width(100.dp).height(100.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Text(text="Let's get to know you", modifier = Modifier.padding(10.dp),)

        OutlinedTextField(
            value = firstname,
            onValueChange = {firstname = it},
            label = {Text(text = "First name") },
            modifier = Modifier
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = lastname,
            onValueChange = {lastname = it},
            label = {Text(text = "Last name") },
            modifier = Modifier
                .padding(top = 8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text(text = "Password") },
            modifier = Modifier
                .padding(top = 8.dp)
        )
        Button(
            onClick = {
                if (firstname.isBlank() || lastname.isBlank() || email.isBlank()) {
                    isSuccess = false
                    statusMessage = "Registration unsuccessful. Please enter all data."
                } else {
                    // Persist user data in SharedPreferences
                    val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    prefs.edit()
                        .putString("first_name", firstname)
                        .putString("last_name", lastname)
                        .putString("email", email)
                        .apply()

                    isSuccess = true
                    statusMessage = "Registration successful!"

                    // Navigate to Home, removing Onboarding from the back stack
                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Register")
        }

        // Status feedback message
        if (statusMessage.isNotEmpty()) {
            Text(
                text = statusMessage,
                color = if (isSuccess) Color(0xFF4CAF50) else Color(0xFFF44336),
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(navController = rememberNavController())
}