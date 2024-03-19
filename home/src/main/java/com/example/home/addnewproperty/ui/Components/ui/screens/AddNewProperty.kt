package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBusiness
import androidx.compose.material.icons.outlined.AutoAwesomeMotion
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.home.addnewproperty.ui.Components.ui.components.AddNewListing
import com.example.home.addnewproperty.ui.Components.ui.components.ExitButton
import com.example.home.destinations.AddNewScreen2Destination
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
@Destination
@Composable
fun AddNewProperty(navigator: DestinationsNavigator){
    Column(
    modifier=Modifier.padding(20.dp)
)
{
    ExitButton(modifier=Modifier.clickable{
        navigator.navigate(MainhomeDestination,false)
    })
    Text(text = "Welcome",
        modifier= Modifier
            .align(Alignment.Start)
            .padding(top = 40.dp),
        style=MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W900),

    )
    Text(text = "Start New Listing",
        modifier= Modifier
            .align(Alignment.Start)
            .padding(top = 30.dp, bottom = 20.dp),
        style=MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W600)
    )
    AddNewListing(
        icon =Icons.Outlined.AddBusiness,
        text = "Create a new Listing",
        navigator = navigator,
        modifier=Modifier
            .clickable {
                navigator.navigate(AddNewScreen2Destination)
            }
    )
    AddNewListing(
        icon =Icons.Outlined.AutoAwesomeMotion,
        text = "Duplicate an existing listing",
        navigator = navigator,
        modifier = Modifier
            .clickable{
                navigator.navigate(AddNewScreen2Destination)
            }
    )
}
}

