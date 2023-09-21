@file:OptIn(ExperimentalMaterial3Api::class)

package com.praveen.spacexapp

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.CornerShape
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.NeuShape
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.praveen.spacexapp.ui.theme.AppColors
import com.praveen.spacexapp.ui.theme.SpaceXAppTheme

@Composable
fun ShowProgressbar(progressState: Boolean) {
    if(progressState){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardIcon(size: Dp=40.dp,defaultElevation:Dp=5.dp,shape: NeuShape,icon:Any,onClick:()->Unit={}) {

Surface(onClick=onClick) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(size)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = defaultElevation,
                lightSource = LightSource.LEFT_TOP,
                shape = shape,
            )
            .clip(getDpForIcon(shape)),
    ) {
        if(icon is ImageVector)
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                imageVector = icon,
                contentDescription = "Person Icon",
                tint = MaterialTheme.colorScheme.primary,

                )
        else
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.ic_ship),
                contentDescription = "Flat image 1",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
    }
}

}

@Composable
fun CardImage(modifier: Modifier=Modifier,size: Dp=40.dp,defaultElevation:Dp=10.dp,shape: NeuShape=Flat(RoundedCorner(24.dp)),
              icon:String,onClick:()->Unit={},
lightSource: LightSource=LightSource.LEFT_BOTTOM) {

   // Surface(onClick=onClick) {
        Card(
            modifier = modifier
                .size(size)
                .neu(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = defaultElevation,
                    lightSource = lightSource,
                    shape = shape,
                )
                .clip(getDpForIcon(shape)),
        ) {
            val painter =
                rememberImagePainter(data = icon)
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    painter = painter,
                    contentDescription = "Flat image 1",
                    contentScale = ContentScale.Fit)

        }
   // }

}
fun getDpForIcon(shape: NeuShape):RoundedCornerShape{
return if(shape.cornerShape==Oval) RoundedCornerShape(24.dp) else RoundedCornerShape(0.dp)
}


@Composable
fun IconCard(size: Dp = 30.dp,modifier: Modifier=Modifier) {
    Image(
        modifier = modifier
            .size(size)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 10.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(Oval),
            ),
        painter = painterResource(id = R.drawable.ic_ship),
        contentDescription = "Pressed image 1",
        contentScale = ContentScale.Inside,
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),

    )
}



@Composable
fun CircleActionButton(modifier: Modifier) {
    val imageSize = 48.dp
    val defaultElevation = 5.dp
    LazyRow(

        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        item {
            Image(
                modifier = Modifier
                    .size(imageSize)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(Oval),
                    ),
                painter = painterResource(id = R.drawable.ic_ship),
                contentDescription = "Pressed image 1",
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
        // Spacer(modifier = Modifier.width(10.dp))
        item {
            Image(
                modifier = Modifier
                    .size(imageSize)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(Oval),
                    ),
                painter = painterResource(id = R.drawable.ic_ship),
                contentDescription = "Pressed image 2",
                contentScale = ContentScale.Inside,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )

        }
        // Spacer(modifier = Modifier.width(10.dp))

        item {
            Card(
                modifier = Modifier
                    .size(imageSize)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ship),
                    contentDescription = "Flat image 1",
                    contentScale = ContentScale.None,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }
        // Spacer(modifier = Modifier.width(10.dp))
        item {
            Card(
                modifier = Modifier
                    .size(imageSize)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ship),
                    contentDescription = "Flat image 2",
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }


        //  Spacer(modifier = Modifier.width(10.dp))
        item {
            Card(
                modifier = Modifier
                    .size(imageSize)
                    .neu(
                        lightShadowColor = AppColors.lightShadow(),
                        darkShadowColor = AppColors.darkShadow(),
                        shadowElevation = defaultElevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(Oval),
                    ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_ship),
                    contentDescription = "Flat image 2",
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }

    ElevatedButton(onClick = { /*TODO*/ }) {
        
    }
}

@Preview
@Composable
fun PriviewComponents() {
    SpaceXAppTheme() {
        CardIcon(shape = Flat(RoundedCorner(24.dp)), icon = painterResource(id = R.drawable.ic_ship))
    }
}