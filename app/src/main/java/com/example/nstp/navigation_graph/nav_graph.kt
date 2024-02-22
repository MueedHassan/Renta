package com.example.nstp.navigation_graph


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.Mainhome
import com.example.nstp.entities.Screen

@Composable
fun Nav() {
    val navController = rememberNavController()
    val firstScreen = Screen.MainHome.route


    NavHost(navController = navController, startDestination = firstScreen) {


        composable(Screen.SignIn.route) {
//            SignIn(navController = navController)
        }
        composable(Screen.MainHome.route) {
//            Mainhome()
        }

//        navigation( startDestination =Screen.MainHome.route,
//            route = "home"
//            )
//        {
//            composable(Screen.MainHome.route) {
//                Mainhome(navController)
//            }
//            composable("addproperty") {
//                AddNewPropertyMainScreen()
//            }
//        }

        // Add more destinations using composable() if needed
    }
}

