package com.example.home.Auth.Components.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldWithIcon(
    labels: String,
    placeholders: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable () -> Unit
) {
    val textState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = modifier,
        label = { Text(text = labels) },
        placeholder = { Text(text = placeholders) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        trailingIcon = trailingIcon,
        keyboardActions = KeyboardActions(
            onDone = { /* Handle Done button click here */ }
        )
    )
}
@Composable
fun TextField(
    labels:String,
    placeholders:String,
    modifier: Modifier=Modifier,


    ): String {
    val textState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        modifier = Modifier.then(modifier) ,
        label = { Text("$labels") },
        placeholder = { Text("$placeholders") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,


            ),
        keyboardActions = KeyboardActions(
            onDone = { /* Handle Done button click here */ },

            )
    )
    return textState.value
}
@Composable
fun TextBody(

    modifier: Modifier = Modifier,
    colors: Color, symbol: String){

    Text(
        text = symbol,
        fontSize =16.sp,
        color = colors,
        fontFamily = FontFamily.Default,
        modifier = Modifier.then((modifier))



    )
}
@Composable
fun SecondHeading(
    modifier: Modifier = Modifier,
    colors: Color, symbol: String,
) {
    Text(text = symbol,
        style = MaterialTheme.typography.titleMedium,

        fontFamily = FontFamily.Default,

        modifier = Modifier.then(modifier))



}
@Composable
fun FilledButtonExample(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    symbols:String

) {
    Button(
        onClick =
        { onClick() },
        modifier = Modifier.then((modifier))
    )
    {
        Text(
            text = symbols)
    }
}

@Composable
fun getwidth(): Int {
    val configuration = LocalConfiguration.current
    return remember(configuration) {
        configuration.screenWidthDp
    }
}

@Composable
fun getheight(): Int{
    val configuration = LocalConfiguration.current
    return remember(configuration) {
        configuration.screenHeightDp
    }
}
@Composable
fun FirstHeading(
    modifier: Modifier =Modifier,
    symbol:String,
    colors: androidx.compose.ui.graphics.Color
){
    Text(
        text = symbol,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = FontFamily.Default,
        modifier = Modifier.then(modifier))

}