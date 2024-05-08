package com.example.home.Recommendation.ui.ui
import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.Mainhome
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.sql.Date
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun BookingPage(navigator: DestinationsNavigator) {
    var adults by rememberSaveable { mutableStateOf(1) }
    var children by rememberSaveable { mutableStateOf(0) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))
    val state = rememberDateRangePickerState()
    val vm:BookingViewModel= viewModel()
    var startDateFormatted by remember {
        mutableStateOf("")
    }
    var endDateFormatted by remember {
        mutableStateOf("")
    }
    var isCLicked by remember {
      mutableStateOf((false))
    }
    val context= LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text="Booking",
             style=MaterialTheme.typography.headlineMedium,
             color=MaterialTheme.colorScheme.primary,
             modifier = Modifier.padding(bottom=10.dp)
            )
        Divider(modifier=Modifier.background(
            color=MaterialTheme.colorScheme.outline)
        )
        Text(text="Select Duration Of Stay",
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom=10.dp)
        )
        Box(
            modifier= Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(100.dp)
                )
                .fillMaxWidth(0.40f)
                .height(40.dp)
                .padding(top = 10.dp)
                .clickable {
                    isCLicked = true
                }

        ){
            Text(
                text = "Select Date",
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.align(Alignment.Center)

            )

        }
        if(isCLicked)
        {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DatePickerDefaults.colors().containerColor)
                        .padding(start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { isCLicked=false }) {
                        Icon(Icons.Filled.Close, contentDescription = "Localized description")
                    }
                    TextButton(
                        onClick = {
                            snackScope.launch {
                                val range =
                                    state.selectedStartDateMillis!!..state.selectedEndDateMillis!!
                                snackState.showSnackbar("Saved range (timestamps): $range")
                                val startDateMillis = range.first
                                val endDateMillis = range.last
                                withContext(Dispatchers.Main){
                                    startDateFormatted = formatMillisToDate(startDateMillis)
                                    endDateFormatted = formatMillisToDate(endDateMillis)
                                }

                            }
                        },
                        enabled = state.selectedEndDateMillis != null
                    ) {
                        Text(text = "Save",
                            modifier=Modifier.clickable {
                                isCLicked=false
                            }
                        )
                    }
                }
                DateRangePicker(state = state, modifier = Modifier.weight(1f))
            }
        }
        Divider(modifier=Modifier.background(
            color=MaterialTheme.colorScheme.outline)
        )
        Text(text="Number Of Adults",
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom=5.dp)
        )
        OutlinedTextField(
            value = adults.toString(),
            onValueChange = { adults = it.toIntOrNull() ?: 0 },
            label = { Text("Adults") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Divider(modifier=Modifier.background(
            color=MaterialTheme.colorScheme.outline)
        )
        Text(text="Number Of Children",
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom=10.dp)
        )
        OutlinedTextField(
            value = children.toString(),
            onValueChange = { children = it.toIntOrNull() ?: 0 },
            label = { Text("Children") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = { vm.viewModelScope.launch {
                vm.bookHotel(adults = adults,children=children,
                    startdate = startDateFormatted,
                    enddate = endDateFormatted,
                    context = context)

            }
                      navigator.navigate(MainhomeDestination)},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Confirm Booking")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(){


}
fun formatMillisToDate(millis: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
    return formatter.format(date)
}