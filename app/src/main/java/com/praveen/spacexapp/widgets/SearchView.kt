package com.praveen.spacexapp.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.praveen.spacexapp.ui.theme.AppColors
import com.praveen.spacexapp.utilities.defaultElevation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(searchText:String,onValueChange:(String)->Unit={}) {
    Card(modifier = Modifier.padding(10.dp)
        .neu(
            lightShadowColor = AppColors.lightShadow(),
            darkShadowColor = AppColors.darkShadow(),
            shadowElevation = defaultElevation,
            lightSource = LightSource.LEFT_TOP,
            shape = Pressed(RoundedCorner(24.dp)),
        ),
    ){
        TextField(
            value = searchText,
            onValueChange = { value ->
                onValueChange(value)
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    tint=MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onValueChange("")
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            tint=MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor =MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
   // val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView("textState")
}