package com.praveen.spacexapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.praveen.spacexapp.ui.theme.AppColors

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = "",
        icon = Icons.Outlined.Home
    )

    object Forecast : BottomNavItem(
        route = "",
        icon = Icons.Outlined.Info
    )

     object Favorite : BottomNavItem(
        route = "Screen.Favorite.route",
        icon = Icons.Outlined.Favorite
    )
}

@Composable
fun NavBar() {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Forecast,
        BottomNavItem.Favorite

    )
    val defaultCity = "default"
    NavigationBar(
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 2.dp,
                lightSource = LightSource.LEFT_BOTTOM,
                shape = Pressed(RoundedCorner(24.dp)),
            )
            .clip(
                RoundedCornerShape(24.dp)
            )
    ) {
        Row(horizontalArrangement = Arrangement.Center,
            modifier=Modifier.padding(10.dp)) {


            val defaultElevation = 10.dp
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }


            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize().padding(10.dp),
                    imageVector = Icons.Default.Shield,
                    contentDescription = "Person Icon",
                    tint = MaterialTheme.colorScheme.primary

                )
            }
        }

    }
}