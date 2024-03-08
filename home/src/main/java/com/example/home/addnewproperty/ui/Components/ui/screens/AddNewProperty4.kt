package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.components.ProgressIndicator
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.destinations.AddNewScreen1Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddNewScreen4(navigator:DestinationsNavigator){
    Box (modifier = Modifier
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        .fillMaxWidth()
        .fillMaxHeight()){
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            TopRoundedButton(
                text = "Exit & Save",
                modifier = Modifier.align(Alignment.Start),
                navigator = navigator
            )
            Text(
                text = "Tell Us About Some Basic Info",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            AddressRows(icon = R.drawable.citylocation,
                label ="Bedrooms",
                labeltext ="Enter City")
            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.outline
                            .copy(alpha = 0.5f)
                    )
            )
            AddressRows(icon = R.drawable.location,
                label ="Washrooms",
                labeltext ="Enter Your")

            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
        }
        ProgressIndicator(progressindicator = 0.4f,
            navigator = navigator,
            destination = AddNewScreen1Destination,
            modifier = Modifier.align(Alignment.BottomStart))
    }

}
@Composable
fun AddressRows(icon: Int, label: String, labeltext: String) {
    var city by rememberSaveable { mutableStateOf("") }
    Row {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
            .width(40.dp)
            .height(40.dp)
            .align(Alignment.Top)
        )
        {
            Icon(
                painter = painterResource(id = icon),
                contentDescription ="Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier= Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )
        }
        Column (modifier = Modifier.padding(start = 10.dp)){
            Text(
                text = label,
                style= MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W900),
            )
          Washroom()

//            TextField(
//                value = city,
//                onValueChange = {city=it},
//                label ={ Text(text = labeltext) },
//                modifier = Modifier
//                    .fillMaxWidth(0.95f)
//                    .padding(top = 10.dp), // Adjust the width as needed
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
//                )
//
//            )
        }

    }
}
@Composable
fun Washroom()
{
    Row {
        WashroomBathroomButton(text = "1")
        WashroomBathroomButton(text = "1")
        WashroomBathroomButton(text = "1")
    }


}
@Composable
fun WashroomBathroomButton(modifier: Modifier=Modifier,text:String){

    Box (modifier = Modifier
        .clip(shape = RoundedCornerShape(40.dp))
        .border(2.dp, color = MaterialTheme.colorScheme.primary)
        .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        .height(20.dp)
        .padding(horizontal = 10.dp)
        .then(modifier)
    ){
        Text(text = text,
            style = MaterialTheme.typography.bodySmall,
            color=MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.Center)
            )

    }

}