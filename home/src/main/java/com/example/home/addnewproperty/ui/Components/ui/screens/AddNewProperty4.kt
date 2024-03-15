package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.remember
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
import com.example.home.destinations.AddNewScreen5Destination
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
                labeltext ="Enter City",
                flag = false)
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
                labeltext ="Enter Your",
                flag = true)
            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
        }
        ProgressIndicator(
            progressindicator = 0.4f,
            navigator = navigator,
            destination = AddNewScreen5Destination,
            modifier = Modifier.align(Alignment.BottomStart),
//            function = vm.AddDataScreen1(data),
        )
    }
}
@Composable
fun AddressRows(icon: Int, label: String, labeltext: String,flag:Boolean) {
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
            if (flag==true){
                Washroom()
            }
            else
            {
                Bedroom()
            }
        }
    }
}
@Composable
fun Washroom()
{ Row (modifier = Modifier.padding(vertical = 10.dp)){
        WashroomBathroomButton(text = "1", modifier = Modifier.width(40.dp))
        WashroomBathroomButton(text = "2",modifier = Modifier.width(40.dp))
        WashroomBathroomButton(text = "3",modifier = Modifier.width(40.dp))
        WashroomBathroomButton(text = "4",modifier = Modifier.width(40.dp))
        WashroomBathroomButton(text = "5",modifier = Modifier.width(40.dp))
        WashroomBathroomButton(text = "6",modifier = Modifier.width(40.dp))
    }
}
@Composable
fun WashroomBathroomButton(modifier: Modifier=Modifier,text:String){
    var isTouched by remember { mutableStateOf(false) }
    val color = if (isTouched) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha=0.1f)
    }
    val textcolor = if (isTouched) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha=0.9f)
    }
    Box (modifier = Modifier
        .clickable {
            isTouched = !isTouched
        }
        .padding(horizontal = 10.dp)
        .clip(shape = RoundedCornerShape(40.dp))
        .border(
            1.dp, color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(40.dp)
        )
        .background(color = color)
        .height(40.dp)
        .then(modifier)
    ){
        Text(text = text,
            style = MaterialTheme.typography.bodySmall,
            color=textcolor,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .align(Alignment.Center)
            )
    }
}
@Composable
fun Bedroom(){
    Column() {
        Row (modifier = Modifier.padding(vertical = 10.dp)){
            WashroomBathroomButton(text = "Studio", modifier = Modifier.width(80.dp))
            WashroomBathroomButton(text = "1", modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "2",modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "3",modifier = Modifier.width(40.dp))
        }
        Row (modifier = Modifier.padding(vertical = 10.dp)){
            WashroomBathroomButton(text = "4", modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "5",modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "6",modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "7",modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "8",modifier = Modifier.width(40.dp))
            WashroomBathroomButton(text = "9",modifier = Modifier.width(40.dp))
        }
    }
}