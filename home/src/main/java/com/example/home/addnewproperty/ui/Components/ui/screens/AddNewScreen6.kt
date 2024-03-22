package com.example.home.addnewproperty.ui.Components.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.components.ProgressIndicator
import com.example.home.addnewproperty.ui.Components.ui.components.TopRoundedButton
import com.example.home.addnewproperty.ui.Components.ui.vm.AddNewPropertyViewModel
import com.example.home.destinations.AddnewScrren7Destination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Destination
@Composable
fun AddNewScreen6(navigator: DestinationsNavigator) {
    var selectedImageUris by remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val addNewPropertyViewModel: AddNewPropertyViewModel = koinViewModel<AddNewPropertyViewModel>()
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedImageUris = uris.take(6)
        }
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp) )
    {
        Column(
        ) {
            TopRoundedButton(
                text = "Exit & Save",
                modifier = Modifier.padding(vertical = 10.dp),
                navigator = navigator
            )
            Text(
                text = "Show Some Photos of your Property to World",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(vertical = 10.dp)
            )
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
                        text = "Upload Images Of Your Property",
                        style= MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W900),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Row(){
                        Icon(
                            painter = painterResource(id = R.drawable.tick),
                            contentDescription ="Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(24.dp)
                                .padding(end = 8.dp)
                            ,)
                        Text(
                            text = "Upload At Max 5 Photos of your property",
                            style= MaterialTheme.typography.bodySmall,
                        )
                    }
                    Row(){
                        Icon(
                            painter = painterResource(id = R.drawable.tick),
                            contentDescription ="Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(24.dp)
                                .padding(end = 8.dp)
                            ,)
                        Text(
                            text = "Please show Clear photos with clear background",
                            style= MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(0.5f)
                    .height(50.dp),
                onClick = { multiplePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) })
            {
                Text(text = "Pick multiple photo")
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                items(selectedImageUris.size) { Uri ->
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .background(
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )
                    ) {
                        AsyncImage(
                            model = selectedImageUris[Uri],
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        ProgressIndicator(
            progressindicator = 0.8f,
            navigator = navigator,
            destination = AddnewScrren7Destination,
            modifier = Modifier.align(Alignment.BottomStart),
            buttonmodifier = Modifier.
            clickable {
                addNewPropertyViewModel.viewModelScope.launch {
                    val imageuris=addNewPropertyViewModel.uploadImagesAndSaveUrls(selectedImageUris)
                    val data:HashMap<String,Any?> = hashMapOf(
                        "List Of Images Uris" to imageuris
                    )
                    addNewPropertyViewModel.UpdateDoc(data,addNewPropertyViewModel.getRecentId())
                }
                navigator.navigate(AddnewScrren7Destination)
            }
        )
    }


}
