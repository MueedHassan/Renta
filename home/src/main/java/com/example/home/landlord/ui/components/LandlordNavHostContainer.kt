package com.example.home.landlord.ui.components
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.home.landlord.ui.LandLordHomeScreen
import com.example.home.landlord.ui.LandlordHome
import com.example.home.landlord.ui.ListingScreen
import com.example.home.landlord.ui.Reservation
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun LandLordNavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    navigator: DestinationsNavigator
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                LandLordHomeScreen(navigator = navigator)
            }
            composable("favourites") {
                Reservation(navigator = navigator)
            }
            composable("bookappointmentscreen") {
                 ListingScreen(navigator=navigator)
            }
        })
}


