package com.example.home.addnewproperty.ui.Components.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination

@Destination

@Composable
fun AddNewScreen1()
{

    Column (    modifier= Modifier.padding(20.dp)
    ){

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription ="Back Arrow",
            tint=MaterialTheme.colorScheme.outline,
            modifier = Modifier.align( Alignment.Start)
        )



    }

}
