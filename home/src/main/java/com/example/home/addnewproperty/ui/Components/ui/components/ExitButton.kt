package com.example.home.addnewproperty.ui.Components.ui.components

import androidx.compose.foundation.border
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

@Composable
fun ExitButton(modifier: Modifier=Modifier) {
    Box (modifier= Modifier
        .width(100.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(20.dp))
        .then(modifier)

        .border(
            1.dp, color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(20.dp)
        )

    ){
        Text(text = "Exit",
            modifier=
            Modifier.align(Alignment.Center),
            style= MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400)


        )


    }
}