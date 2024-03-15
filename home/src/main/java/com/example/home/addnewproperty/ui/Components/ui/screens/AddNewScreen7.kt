package com.example.home.addnewproperty.ui.Components.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.components.ProgressIndicator
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddnewScrren7(navigator: DestinationsNavigator){
    val rentaltype= listOf("Long Term Rental","Short Term Rental","Only Family ","All Boys","All Girls")

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
                text = "Ohh! Thats The Last Step Of Your Journey",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Spacer(
                modifier = Modifier.height(40.dp)
            )
            AddressRow(icon = R.drawable.person,
                label ="Enter Maximun number of Person Allowed\"" ,
                labeltext ="Maximum Occupancy"
            )
            Row (modifier = Modifier.padding(top = 10.dp))
            {
                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                    .width(40.dp)
                    .height(40.dp)
                    .align(Alignment.Top)
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription ="Icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier= Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )
                }

                Column (modifier = Modifier.padding(start = 10.dp))
                {
                     Text(
                        text = "Property Rental Status",
                        style= MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W900),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    LazyHorizontalGrid(rows = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp) ,
                        modifier = Modifier.height(100.dp)

                    ) {
                        items(rentaltype.size){item->
                           WashroomBathroomButton(text = rentaltype[item])

                        }
                        
                    }
                

                }

            }
            PhoneNumberInputWithCountryDropdown()
            AddressRow(icon = R.drawable.size, label = "Email", labeltext ="@yahoo.com")
            

        }
        ProgressIndicator(
            progressindicator = 1f,
            navigator =navigator,
            destination = MainhomeDestination,
            modifier = Modifier.align(Alignment.BottomStart),
            flag =true,
//            function = vm.AddDataScreen1(data),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberInputWithCountryDropdown() {
    var phoneNumber by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("US(966)") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                .width(40.dp)
                .height(40.dp)
                .align(Alignment.Top)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.images),
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
            )
        }

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = "Phone Number",
                style= MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W900),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(0.4f))
                        .clickable { isDropdownExpanded = true }
                ) {

                        Text(
                            text = selectedCountry,
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Alignment.Center)

                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .align(Alignment.CenterEnd)

                        )

                }

                TextField(
                    value = phoneNumber,
                    onValueChange = {
                        phoneNumber = it
                    },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        capitalization = KeyboardCapitalization.None,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    colors = textFieldColors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        unfocusedTextColor = MaterialTheme.colorScheme.outline
                    )
                )
            }

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                listOf("US(45)", "UK(988)", "CA(34)", "IN(91)", "Pk(92)").forEach { country ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCountry = country
                            isDropdownExpanded = false
                        }
                    ) {
                        Text(text = country)
                    }
                }
            }
        }
    }
}