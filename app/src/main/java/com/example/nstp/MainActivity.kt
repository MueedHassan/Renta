package com.example.nstp
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.applovin.sdk.AppLovinSdk
import com.applovin.sdk.AppLovinSdkConfiguration
import com.example.home.NavGraphs
import com.example.home.addnewproperty.ui.Components.ui.di.mainModule
import com.example.nstp.components.ui.theme.RentaTheme
import com.google.firebase.FirebaseApp
import com.ramcosta.composedestinations.DestinationsNavHost
import org.koin.compose.KoinContext
import org.koin.core.context.GlobalContext.startKoin


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



