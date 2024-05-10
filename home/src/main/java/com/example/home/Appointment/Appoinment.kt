package com.example.home.Appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(navigator: DestinationsNavigator) {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }
    val vm:AppointmentViewModel= viewModel()
    var key by remember { mutableStateOf(false) }
    val context= LocalContext.current
Box(modifier = Modifier.fillMaxSize()) {
    Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    horizontalAlignment = Alignment.Start,

    ) {
    Text(text="Booking",
        style=MaterialTheme.typography.headlineMedium,
        color=MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom=10.dp)
    )


    Text(text="Number Of Adults",
        style=MaterialTheme.typography.bodyMedium,
        color=MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom=5.dp)
    )
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Name") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        modifier = Modifier.fillMaxWidth()
    )
        Divider(modifier = Modifier.padding(vertical = 24.dp))
        Text(text="Select Appointment date",
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom=5.dp)
        )

    Text(text = if (date=="")"No Date Selected" else date, modifier = Modifier.padding(bottom = 16.dp))

    Button(
        onClick = {
            showDatePicker = true
            date= vm.formatDate(datePickerState.selectedDateMillis).toString()
            key=true
        },
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .height(35.dp),
    ) {
        Text(text = "Date Picker")
    }

    Divider(modifier = Modifier.padding(vertical = 24.dp))
        Text(text="Select Appointment Time",
            style=MaterialTheme.typography.bodyMedium,
            color=MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom=5.dp)
        )
    Text(text = if (time=="")"No Time Selected" else time, modifier = Modifier.padding(bottom = 16.dp))

    Button(
        onClick = {
            showTimePicker = true
            time=vm.formatTime(timePickerState.hour,timePickerState.minute)
            key=true

        },
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .height(35.dp),
    ) {
        Text(text = "Time Picker")
    }

    }
    Button(
        onClick = {
            vm.viewModelScope.launch {
                vm.storeAppointment(name=name,time=time, date = date,context=context)
            }
            navigator.navigate(MainhomeDestination)
        },
        modifier = Modifier
            .padding(bottom=20.dp)
            .fillMaxWidth(0.8f).height(48.dp)
            .align(Alignment.BottomCenter),
    ) {
        Text(text = "Book Appointment")
      }

    }

    if (showDatePicker) {

        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            DatePicker(state = datePickerState)
        }
        date= vm.formatDate(datePickerState.selectedDateMillis).toString()

    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
        time=vm.formatTime(timePickerState.hour,timePickerState.minute)
    }

}
@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}