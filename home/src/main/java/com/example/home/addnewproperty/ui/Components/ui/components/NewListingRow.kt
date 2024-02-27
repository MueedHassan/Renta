package com.example.home.addnewproperty.ui.Components.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun AddNewListing(
    icon: ImageVector,
    text: String,
    navigator: DestinationsNavigator,
    modifier: Modifier


){
    Box(
        modifier = Modifier
            .padding(top=10.dp)
            .fillMaxWidth()
            .then(modifier)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = icon,
                contentDescription = "Pro icon",
                tint = MaterialTheme.colorScheme.outline
            )
            Text(
                modifier = Modifier.padding(start = 40.dp),
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline

            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(32.dp),

            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "Pro icon",
            tint = MaterialTheme.colorScheme.outline,

            )
    }






}