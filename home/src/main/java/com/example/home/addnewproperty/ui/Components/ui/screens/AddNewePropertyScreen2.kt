package com.example.home.addnewproperty.ui.Components.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.addnewproperty.ui.Components.ui.vm.AddNewPropertyViewModel
import com.example.home.destinations.AddNewScreen3Destination
import com.google.firebase.firestore.FieldValue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun AddNewScreen2(
    navigator: DestinationsNavigator,
)
{ val addNewPropertyViewModel:AddNewPropertyViewModel = koinViewModel<AddNewPropertyViewModel>()
    var selectedType by remember { mutableStateOf<String?>(null) }
    var homeTypeIcon = listOf(
        Icons.Filled.AddHome
        ,Icons.Filled.AddBusiness,
        Icons.Filled.AddHome
        ,Icons.Filled.AddHome
        ,Icons.Filled.AddHome
        ,Icons.Filled.AddHome,
        Icons.Filled.AddHome
    )
    var textList = listOf("Home ","Villa","Flat ","Studio","Bungalow","Single Room","Palace")
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
        )
        {
            TopRoundedButton(
                text = "Exit & Save",
                modifier = Modifier.align(Alignment.Start),
                navigator = navigator
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(homeTypeIcon.size) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth() // Make Row take the full width
                            .padding(horizontal = 10.dp)
                    )
                    {
                        HomeTypeRow(
                            homeTypeIcon[index],
                            textList[index],
                            selectedType == textList[index]
                        ) { selected ->
                            selectedType = if (selected) textList[index] else null
                        }

                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Adjust spacing as needed
                }
            }

        }
        val data = hashMapOf(
            "PlaceType" to selectedType,
            "timestamp" to FieldValue.serverTimestamp(),
            "ownerId" to addNewPropertyViewModel.getCurrentUserId()
        )
        var progressindicator = 0.2f
        var destination = AddNewScreen3Destination
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
                                addNewPropertyViewModel.addProperty(data)
                            }
                            navigator.navigate(AddNewScreen3Destination)
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


    }
}

@Composable
fun HomeTypeRow(
    imageVector: ImageVector,
    s: String,
    isSelected: Boolean,
    onSelectedChanged: (Boolean) -> Unit
    ){
    val color = if ( isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)
    }
        Box (modifier= Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = color.copy(alpha = 0.1f)
            )
            .clickable {
                onSelectedChanged(!isSelected)

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
                tint = color.copy(alpha = 1f),
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
                color =color.copy(alpha = 1f)
            )
    }
}
