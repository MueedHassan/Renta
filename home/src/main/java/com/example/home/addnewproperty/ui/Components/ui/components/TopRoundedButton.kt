package com.example.home.addnewproperty.ui.Components.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun TopRoundedButton(
    text: String,
    modifier: Modifier,
    navigator: DestinationsNavigator,


    )
{ Box (modifier= Modifier
    .then(modifier)
        .width(100.dp)
        .height(40.dp)
        .clickable { navigator.navigate(
            MainhomeDestination
            ) }
        .clip(RoundedCornerShape(20.dp))
        .border(
            1.dp, color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(20.dp))

    ){
    Text(text = text,
            modifier=
            Modifier.align(Alignment.Center),
            style= MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400))
    }
}