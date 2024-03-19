package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.background
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
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewModelScope
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.addnewproperty.ui.Components.ui.vm.AddNewPropertyViewModel
import com.example.home.destinations.AddNewScreen4Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun AddNewScreen3(navigator: DestinationsNavigator){
    var dataupdated by rememberSaveable { mutableStateOf(hashMapOf<String, Any?>()) }
    var city by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    val addNewPropertyViewModel: AddNewPropertyViewModel = koinViewModel<AddNewPropertyViewModel>()
    var id:String?=null
    var showLaunchedEffect by rememberSaveable { mutableStateOf(false) }
    val data :HashMap<String,Any?> = hashMapOf(
        "City" to city,
        "Location" to location,
        "Area" to area,
        "Price" to price
    )

    Box(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            TopRoundedButton(
                text = "Exit & Save",
                modifier = Modifier.align(Alignment.Start),
                navigator = navigator
            )
            Text(
                text = "Tell Us About Your Location",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            city =
                AddressRow(icon = R.drawable.citylocation, label = "City", labeltext = "Enter City")

            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
            location =
                AddressRow(icon = R.drawable.location, label = "Location", labeltext = "Enter Your")

            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
            area = AddressRow(
                icon = R.drawable.size,
                label = "Area",
                labeltext = "Enter Area in square feat"
            )

            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
            price = AddressRow(
                icon = R.drawable.price,
                label = "Price",
                labeltext = "Enter An Estimated Prize"
            )

            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            )
        }
        val data :HashMap<String,Any?> = hashMapOf(
            "City" to city,
            "Location" to location,
            "Area" to area,
            "Price" to price
        )
            var progressindicator = 0.2f
            var modifier = Modifier.align(Alignment.BottomStart)
            var flag=false

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .then(modifier)
            ) {
                Row() {
                    LinearProgressIndicator(
                        progress = progressindicator,
                        modifier = Modifier
                            .height(5.dp)
                            .fillMaxWidth()
                    )
                }
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .width(80.dp)
                        .padding(10.dp)
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        )
                        .align(Alignment.CenterEnd)
                        .clickable {
                            addNewPropertyViewModel.viewModelScope.launch {
                                addNewPropertyViewModel.UpdateDoc(data,
                                    addNewPropertyViewModel.getRecentId())
                            }
                            navigator.navigate(AddNewScreen4Destination)
                        }
                )
                {
                    val value:String
                    if(flag==true)
                    {
                        value="Finish"
                    }
                    else
                    {
                        value="Next"
                    }
                    androidx.compose.material3.Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }}
            }
}
@Composable
fun AddressRow(icon: Int, label: String, labeltext: String): String {
    var city by rememberSaveable { mutableStateOf("") }
    Row {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                .width(40.dp)
                .height(40.dp)
                .align(Alignment.Top)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )
        }
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W900),
            )
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(text = labeltext) },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 10.dp), // Adjust the width as needed
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                )
            )
        }
    }
    return city
}

