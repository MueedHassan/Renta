package com.example.home.tenants.module

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home.tenants.module.ui.BookingAppointment.ui.BookAppointmentScreen
import com.example.home.tenants.module.ui.Favourite.FavouritesScreen
import com.example.home.tenants.module.ui.Home.ui.TenantHome
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    navigator: DestinationsNavigator,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
               TenantHome(navigator=navigator)
               return@composable
            }
            composable("favourites") {
                FavouritesScreen(navigator=navigator)
            }
            composable("bookappointmentscreen") {
                BookAppointmentScreen(navigator=navigator)
            }
        })
}
