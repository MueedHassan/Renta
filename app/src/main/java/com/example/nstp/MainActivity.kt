package com.example.nstp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.home.NavGraphs
//import com.example.home.NavGraphs
import com.example.nstp.components.ui.theme.RentaTheme
import com.example.nstp.navigation_graph.Nav
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    DestinationsNavHost(navGraph = NavGraphs.root)
//                    Nav()
                 DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

