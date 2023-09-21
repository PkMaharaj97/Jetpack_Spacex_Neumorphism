package com.praveen.spacexapp.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import com.gandiva.neumorphic.shape.RoundedCorner

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.CornerShape
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.praveen.spacexapp.ui.theme.AppColors
import com.praveen.spacexapp.utilities.defaultElevation

@Composable
fun ListRadioButton(onSelected:(String)->Unit,options:List<String>) {
    var selectedOption by remember { mutableStateOf("All") }

    LazyRow {
        options.forEach { option ->
            item {
                Button(
                    onClick = {
                        selectedOption = option
                        onSelected(selectedOption)
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .neu(
                            lightShadowColor = AppColors.lightShadow(),
                            darkShadowColor = AppColors.darkShadow(),
                            shadowElevation = defaultElevation,
                            lightSource = LightSource.LEFT_TOP,
                            shape = if(selectedOption == option)Pressed(RoundedCorner(24.dp)) else Flat(RoundedCorner(24.dp)),
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor =  MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.primary

                        //containerColor = if (selectedOption == option) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                ) {

                    Text(text = option)
                }

            }
        }
    }
}
