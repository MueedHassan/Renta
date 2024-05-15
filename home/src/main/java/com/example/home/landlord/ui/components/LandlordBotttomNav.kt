package com.example.home.landlord.ui.components

import com.example.home.R
import com.example.home.landlord.entities.LandLordBottomItem


object LandLordConstants {
    val LandLordBottomNavItems = listOf(
        LandLordBottomItem(
            label = "Home",
            icon = R.drawable.icons8_home,
            route = "home"
        ),
        LandLordBottomItem  (
            label = "Reservation",
            icon = R.drawable.icons8_favourite,
            route = "favourites"
        ),
        LandLordBottomItem(
            label = "Listing",
            icon = R.drawable.icons8_appointment_80,
            route = "bookappointmentscreen"
        ),
    )
}
