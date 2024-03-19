package com.example.home.tenants.module

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home.tenants.module.ui.BookAppointmentScreen
import com.example.home.tenants.module.ui.FavouritesScreen

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("home") {
                HomeScreen()
            }
            composable("favourites") {
                FavouritesScreen()
            }
            composable("bookappointmentscreen") {
                BookAppointmentScreen()
            }
        })
}
