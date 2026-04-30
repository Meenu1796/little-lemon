package com.coursera.capstone

interface Destinations {
    val route: String
    val icon: Int
    val title: String
}

object Onboarding : Destinations {
    override val route = "Onboarding"
    override val icon = R.drawable.edit
    override val title = "Onboarding"
}


object Home : Destinations {
    override val route = "Home"
    override val icon = R.drawable.home
    override val title = "Home"
}


object Profile : Destinations {
    override val route = "Profile"
    override val icon = R.drawable.user
    override val title = "Profile"
}