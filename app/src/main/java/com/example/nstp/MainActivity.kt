package com.example.nstp
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
import com.example.nstp.components.ui.theme.RentaTheme
import com.ramcosta.composedestinations.DestinationsNavHost
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Make sure to set the mediation provider value to "max" to ensure proper functionality
            AppLovinSdk.getInstance( this ).setMediationProvider( "max" )
            AppLovinSdk.getInstance( this ).initializeSdk({ configuration: AppLovinSdkConfiguration ->
                // AppLovin SDK is initialized, start loading ads
            })

        super.onCreate(savedInstanceState)
        setContent {
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
}

