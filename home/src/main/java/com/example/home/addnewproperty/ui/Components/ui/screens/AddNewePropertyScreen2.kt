package com.example.home.addnewproperty.ui.Components.ui.screens
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.home.addnewproperty.ui.Components.ui.components.ProgressIndicator
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.destinations.AddNewScreen3Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun AddNewScreen2( navigator: DestinationsNavigator)
{ var homeTypeIcon = listOf(
        Icons.Filled.AddHome,
        Icons.Filled.AddHome
        ,Icons.Filled.AddHome,Icons.Filled.AddHome
        ,Icons.Filled.AddHome,
        Icons.Filled.AddHome
        ,Icons.Filled.AddHome,Icons.Filled.AddHome
    )
    var textList = listOf("home ", "Boat ","Villa","home ","home ", "Boat ","Villa","home ")
       Column(modifier = Modifier.padding(10.dp))
       {
           TopRoundedButton(
               text = "Exit & Save",
               modifier = Modifier.align(Alignment.Start),
               navigator=navigator
           )
           Text(
               text = "Which of these best describes your place?",
               style = MaterialTheme.typography.headlineMedium,
               color = MaterialTheme.colorScheme.outline,
               modifier = Modifier.padding(vertical = 10.dp)
           )
           Spacer(
               modifier = Modifier.height(40.dp)
           )
           LazyColumn(modifier = Modifier.fillMaxHeight(0.85f)) {
               items(homeTypeIcon.size) { index ->
                   Row(
                       modifier = Modifier
                           .fillMaxWidth() // Make Row take the full width
                           .padding(horizontal = 10.dp)
                   )
                   {
                       HomeTypeRow(homeTypeIcon[index], textList[index])
                       Spacer(modifier = Modifier.width(10.dp))
                       HomeTypeRow(homeTypeIcon[index], textList[index])
                   }
                   Spacer(modifier = Modifier.height(16.dp)) // Adjust spacing as needed
               }
           }
           ProgressIndicator(
               navigator = navigator,
               progressindicator = 0.2f,
               destination = AddNewScreen3Destination,

           )
       }

}
@Composable
fun HomeTypeRow(imageVector: ImageVector, s: String, ) {
    var isTouched by remember { mutableStateOf(false) }

    val color = if (isTouched) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }
        Box (modifier= Modifier
            .clickable {
                isTouched = !isTouched
            }
            .width(165.dp)
            .height(80.dp)
            .border(
                0.5.dp,
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
        ){
            Icon(
                imageVector = imageVector,
                contentDescription = "Icon",
                tint = color,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .align(Alignment.TopStart)
                    .size(25.dp)
                )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, bottom = 10.dp),
                text = s,
                style = MaterialTheme.typography.bodyMedium,
                color =color
            )
    }
}
