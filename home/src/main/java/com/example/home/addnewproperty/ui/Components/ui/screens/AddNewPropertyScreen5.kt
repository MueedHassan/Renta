package com.example.home.addnewproperty.ui.Components.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.components.ProgressIndicator
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.destinations.AddNewScreen6Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun AddNewScreen5(navigator: DestinationsNavigator){

    Box (modifier = Modifier
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        .fillMaxWidth()
        .fillMaxHeight()){
        Column(modifier = Modifier.padding(10.dp)) {
            TopRoundedButton(
                text = "Exit & Save",
                modifier = Modifier.align(Alignment.Start).padding(bottom = 10.dp),
                navigator = navigator
            )
            AddressRow(icon = R.drawable.room,
                label = "Property Tile",
                labeltext ="Enter Propert Tile" )
            Divider(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .background(color = MaterialTheme.colorScheme.outline
                        .copy(alpha = 0.5f))
            )
            AddressRow(icon = R.drawable.houses_property_svgrepo_com,
                label = "Property Description",
                labeltext ="Provide Complete Descrition of your Property" )
        }
        ProgressIndicator(
            progressindicator = 0.6f,
            navigator = navigator,
            destination = AddNewScreen6Destination,
            modifier = Modifier.align(Alignment.BottomStart),
//            function = vm.AddDataScreen1(data),
        )
    }




}