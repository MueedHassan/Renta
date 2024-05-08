package com.example.nstp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.home.NavGraphs
import com.example.nstp.components.ui.theme.RentaTheme
import com.google.firebase.FirebaseApp
import com.ramcosta.composedestinations.DestinationsNavHost
import io.ktor.client.plugins.BodyProgress.Plugin.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import org.koin.compose.KoinContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure to set the mediation provider value to "max" to ensure proper functionality

        super.onCreate(savedInstanceState)

        // Start Koin
        FirebaseApp.initializeApp(applicationContext)
        setContent {
            KoinContext()
            {
                RentaTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        DestinationsNavHost(navGraph = NavGraphs.root)
                    }
                }


            }

    }
}}



