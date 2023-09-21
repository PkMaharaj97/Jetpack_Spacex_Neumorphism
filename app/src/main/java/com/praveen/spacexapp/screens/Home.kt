package com.praveen.spacexapp.screens

import android.icu.text.ListFormatter.Width
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Oval
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.praveen.spacexapp.CardIcon
import com.praveen.spacexapp.CircleActionButton
import com.praveen.spacexapp.ShowProgressbar
import com.praveen.spacexapp.navigation.Screens
import com.praveen.spacexapp.ui.theme.AppColors
import com.praveen.spacexapp.ui.theme.Purple40
import com.praveen.spacexapp.ui.theme.Purple80
import com.praveen.spacexapp.ui.theme.PurpleGrey40
import com.praveen.spacexapp.ui.theme.SpaceXAppTheme
import com.praveen.spacexapp.widgets.AnimatedCircularProgressIndicator
import com.praveen.spacexapp.widgets.NavBar
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,viewModel:HomeVM) {
val progressBarState by viewModel.progressLoading.collectAsState()
    val shipsList by viewModel.listOf_AllShips.collectAsState()
    val launchList by viewModel.listOf_AllLaunches.collectAsState()
val context= LocalContext.current
    LaunchedEffect(Unit){
        viewModel.getAllShips()
    }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

                Scaffold(
                    topBar = {
                             CenterAlignedTopAppBar(
                                 title = {
                                     Text(
                                         "Task !",
                                         maxLines = 1,
                                         overflow = TextOverflow.Ellipsis
                                     )
                                 },
                                 navigationIcon = {
                                     CardIcon(
                                         shape = Flat(RoundedCorner(24.dp)),
                                         icon = Icons.Filled.Menu,
                                         onClick = {
                                             Toast.makeText(context, "Menu", Toast.LENGTH_LONG)
                                                 .show()
                                         })
                                 },
                                 actions = {
                                     CardIcon(
                                         shape = Flat(RoundedCorner(24.dp)),
                                         icon = Icons.Filled.Favorite,
                                         onClick = {
                                             navController.navigate(Screens.LaunchList.route)
                                         }
                                     )

                                 }
                             )

                    },
                    content = { padding ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                        ) {
                            ShowProgressbar(progressState = progressBarState)
                            LazyRow {
                                items(items = shipsList) {
                                    ShipCard(Ship = it)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            LazyRow {
                                items(items = launchList) {
                                    LaunchCard(launchModel = it)
                                }
                                item {
                                    Card(
                                        modifier = Modifier
                                            .size(200.dp)
                                            .padding(20.dp)
                                            .neu(
                                                lightShadowColor = AppColors.lightShadow(),
                                                darkShadowColor = AppColors.darkShadow(),
                                                shadowElevation = 5.dp,
                                                lightSource = LightSource.LEFT_TOP,
                                                shape = Flat(RoundedCorner(24.dp)),
                                            )
                                            .clickable {
                                                navController.navigate(Screens.LaunchList.route)
                                            },
                                        shape = RoundedCornerShape(24.dp),
                                    ){
                                        Column(modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center) {
                                            Text(text = "See All",
                                                style = MaterialTheme.typography.headlineLarge,
                                                textAlign = TextAlign.Center,
                                                color = MaterialTheme.colorScheme.primary)
                                        }


                                    }
                                }
                            }


                            /*Spacer(modifier = Modifier.height(10.dp))
                            LazyRow(Modifier.fillMaxWidth()) {
                                item {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    AnimatedCircularProgressIndicator(
                                        currentValue = 17,
                                        maxValue = 20,
                                        progressBackgroundColor = Purple80,
                                        progressIndicatorColor = PurpleGrey40,
                                        completedColor = Purple40,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .neu(
                                                lightShadowColor = AppColors.lightShadow(),
                                                darkShadowColor = AppColors.darkShadow(),
                                                shadowElevation = 15.dp,
                                                lightSource = LightSource.LEFT_TOP,
                                                shape = Flat(Oval),
                                            ),
                                    )

                                }

                                item {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Card(modifier = Modifier
                                        .padding(5.dp)
                                        .neu(
                                            lightShadowColor = AppColors.lightShadow(),
                                            darkShadowColor = AppColors.darkShadow(),
                                            shadowElevation = 15.dp,
                                            lightSource = LightSource.LEFT_TOP,
                                            shape = Flat(Oval),
                                        ), shape = RoundedCornerShape(100.dp)
                                    ){
                                    AnimatedCircularProgressIndicator(
                                        currentValue = 20,
                                        maxValue = 20,
                                        progressBackgroundColor = Purple80,
                                        progressIndicatorColor = Purple40,
                                        completedColor = Purple40,
                                    )}

                                }

                                item {
                                    CircularProgressAnimated()
                                }

                            }*/

                        }
                    }, bottomBar = {
                        NavBar()
                    },
                    //containerColor = Color.Transparent
                )
            }



}
@Composable
private fun CircularProgressAnimated(){
    val progressValue = 0.75f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900)))

    CircularProgressIndicator(progress = progressAnimationValue)
}
@Composable
fun ShipCard(Ship:ShipPojo)
{

    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 5.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(RoundedCorner(24.dp)),
            ),
        shape = RoundedCornerShape(24.dp),
    ){
        val painter =
            rememberImagePainter(data = Ship.ship_image)

        Image(
            modifier= Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.FillBounds,

        )
    }

}


@Composable
fun LaunchCard(launchModel:LaunchModelPojo)
{
    Log.e("HomeScreen","$launchModel")

    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 5.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Flat(RoundedCorner(24.dp)),
            ),
        shape = RoundedCornerShape(24.dp),
    ){
        val painter =
            rememberImagePainter(data = launchModel.pic )

        Image(
            modifier= Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.FillBounds,

            )
    }

}

@Composable
fun ShipCard2(Ship:ShipPojo)
{

    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(20.dp)
            .neu(
                lightShadowColor = AppColors.lightShadow(),
                darkShadowColor = AppColors.darkShadow(),
                shadowElevation = 5.dp,
                lightSource = LightSource.LEFT_TOP,
                shape = Pressed(RoundedCorner(24.dp)),
            ),
        shape = RoundedCornerShape(24.dp),
    ){
        val painter =
            rememberImagePainter(data = Ship.ship_image)

        Image(
            modifier= Modifier
                .padding(10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp)),
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.FillBounds,

            )
        /*AsyncImage(
            model = Ship.ship_image,
            contentDescription = "This is an example image",
            modifier = Modifier.fillMaxSize()
        )*/
    }

}

@Preview
@Composable
fun Privieww() {
   SpaceXAppTheme() {
       ShipCard(Ship = ShipPojo("Titanic @0233","hy778","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMqLmVjYN3LlA6mM-rHeLztavdHBz6FjrHMQDnaaDU30sfM03UJ2aUAxI9jVktjAGp6Sg&usqp=CAU"))
   } 
}
