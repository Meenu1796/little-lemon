package com.coursera.capstone

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current

    // Retrieve user data from SharedPreferences
    val prefs = remember {
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }
    val firstName = prefs.getString("first_name", "") ?: ""
    val lastName  = prefs.getString("last_name",  "") ?: ""
    val email     = prefs.getString("email",      "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(100.dp).height(100.dp),
            painter = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.Fit,
            contentDescription = "Logo"
        )
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
            text = "Profile Screen",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,)
        // First Name
        Text(
            text = "First name:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = firstName,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color(0xFFF0F0F0))
                .padding(12.dp)
        )
        // Last Name
        Text(
            text = "Last name:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = lastName,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color(0xFFF0F0F0))
                .padding(12.dp)
        )
        // Email
        Text(
            text = "Email:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = email,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(Color(0xFFF0F0F0))
                .padding(12.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) { // Back button — pops back to Home
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Back",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }

            // Logout button — clears user data and navigates to Onboarding,
            // removing Home (and Profile) from the back stack entirely
            Button(
                onClick = {
                    // Clear stored user data from SharedPreferences
                    prefs.edit().clear().apply()

                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier.padding(top = 16.dp).padding(start = 16.dp)
            ) {
                Text(text = "Logout",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
            }}

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(navController = rememberNavController())
}