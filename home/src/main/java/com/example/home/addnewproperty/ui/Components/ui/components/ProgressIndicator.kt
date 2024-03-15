package com.example.home.addnewproperty.ui.Components.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

@Composable
fun ProgressIndicator(
    progressindicator: Float,
    navigator: DestinationsNavigator,
    destination: Direction,
    modifier: Modifier = Modifier,
    flag: Boolean = false,
    function: Unit=Unit,


    ): String {
    var id:String= null.toString()
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
                    function
                    navigator.navigate(destination)
                }
        ) {
            val value:String
            if(flag==true)
            {
                value="Finish"
            }
            else
            {
                value="Next"
            }
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
    return id
}