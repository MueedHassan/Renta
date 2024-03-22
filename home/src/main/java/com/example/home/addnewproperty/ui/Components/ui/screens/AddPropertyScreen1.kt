package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.AirlineSeatFlatAngled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.home.destinations.AddNewScreen2Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
@Destination
@Composable
fun AddNewScreen1(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Its Easy To Get Started on Renta",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 20.dp,
                bottom = 20.dp
            )
        )
        AddNewScrren1Component(
            text = "1. Tell Us About Place",
            subheading = "Sahere Some basic Info ,like how many guest can stay",
            icon = Icons.Filled.AddHome
        )
        Divider(
            color = MaterialTheme.colorScheme.outline,
             modifier = Modifier.alpha(0.40F)
        )
        AddNewScrren1Component(
            text = "2. Make it Standout",
            subheading = "Add 5 or more photos plus title and description of your property I'll help you  ",
            icon = Icons.Filled.Bed
        )
        Divider(color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.alpha(0.40F)

        )
        AddNewScrren1Component(
            text = "3. Finish Up and Publish",
            subheading = "Choose here you want to start with an expierience Guest",
            icon = Icons.Filled.AirlineSeatFlatAngled
        )
        Divider(color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.alpha(0.40F)

            )
    Box(
        modifier =
        Modifier
            .padding(
                top = 240.dp,
                )
            .clickable {
                navigator.navigate(
                    AddNewScreen2Destination
                )
            }
            .height(60.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = MaterialTheme.colorScheme.primary
            )
    ) {

        Text(
            text = "Get Started",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)

        )
    }
}
}
@Composable
fun AddNewScrren1Component(
    text:String,
    subheading:String,
    icon: ImageVector
)
{
    Box (modifier = Modifier
        .fillMaxWidth()
        .height(100.dp))

    {
     Column(modifier = Modifier.padding(end=50.dp)) {
         Text(
             text = text,
             style = MaterialTheme.typography.headlineMedium

         )
         Text(
             text = subheading,
             style = MaterialTheme.typography.bodySmall,
             color = MaterialTheme.colorScheme.outline,
             modifier = Modifier.padding(
                 top = 5.dp,
                 start=30.dp,
                 bottom = 20.dp
             )
         )
     }
        Icon(
            imageVector =icon,
            contentDescription = "Icon home",
            modifier= Modifier
                .height(60.dp)
                .width(60.dp)
                .align(Alignment.CenterEnd)
                .padding(10.dp),
            tint=MaterialTheme.colorScheme.primary

        )
    }

}