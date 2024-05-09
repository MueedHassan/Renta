package com.example.home.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import com.example.home.R

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = R.drawable.icons8_home,
            route = "home"
        ),
        BottomNavItem(
            label = "Favourites",
            icon = R.drawable.icons8_favourite,
            route = "favourites"
        ),
        BottomNavItem(
            label = "Reservation",
            icon =R.drawable.icons8_appointment_80,
            route = "bookappointmentscreen"
        ),
        BottomNavItem(
            label = "Chat",
            icon =R.drawable.icons8_chat_32,
            route = "bookappointmentscreen"
    )
    )
}
