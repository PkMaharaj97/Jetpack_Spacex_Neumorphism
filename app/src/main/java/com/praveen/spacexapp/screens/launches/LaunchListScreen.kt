@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.praveen.spacexapp.screens.launches

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.RoundedCorner
import com.praveen.spacexapp.CardIcon
import com.praveen.spacexapp.CardImage
import com.praveen.spacexapp.ShowProgressbar
import com.praveen.spacexapp.screens.LaunchCard
import com.praveen.spacexapp.screens.ShipCard
import com.praveen.spacexapp.ui.theme.AppColors
import com.praveen.spacexapp.ui.theme.SpaceXAppTheme
import com.praveen.spacexapp.utilities.ImageNotFound
import com.praveen.spacexapp.widgets.ListRadioButton
import com.praveen.spacexapp.widgets.NavBar
import com.praveen.spacexapp.widgets.SearchView

@ExperimentalMaterial3Api
@Composable
fun LaunchListScreen(navController: NavController,viewModel: LaunchListVM) {
    val progressBarState by viewModel.progressLoading.collectAsState()
    val launchList by viewModel.main_launches.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val context= LocalContext.current
    LaunchedEffect(Unit){
        viewModel.getAllLaunches()
    }

    /*Box(
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
                            icon = Icons.Filled.ArrowBackIos,
                            onClick = {
                                Toast.makeText(context, "gvjwvcw", Toast.LENGTH_LONG)
                                    .show()
                            })
                    },
                    actions = {

                    }
                )

            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding),
                ) {
                    LaunchListView(launchList)


                }
            }, bottomBar = {
                NavBar()
            },

        )
    }*/
    Card {
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(5.dp))
            ListRadioButton(onSelected = {viewModel.onNationChanged(it)}, options = viewModel.list_of_nations.toList().sorted())
            Spacer(modifier = Modifier.height(5.dp))
            SearchView(searchText = searchText,viewModel::onSearchText)
            Spacer(modifier = Modifier.height(5.dp))
            LaunchListView(launchList,Modifier.fillMaxSize())

        }

    }
}

@Composable
fun LaunchListView(list:List<LaunchListRowModel>,modifier: Modifier=Modifier) {
Card {
    LazyColumn{
        items(items=list){
            LaunchListItem(launchListRowModel = it)
        }
    }
}
}
@Composable
fun LaunchListItem(launchListRowModel: LaunchListRowModel,modifier: Modifier=Modifier)
{

var infoState by remember { mutableStateOf(false) }

   // Surface(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .neu(
                    lightShadowColor = AppColors.lightShadow(),
                    darkShadowColor = AppColors.darkShadow(),
                    shadowElevation = 20.dp,
                    lightSource = LightSource.RIGHT_TOP,
                    shape = Flat(RoundedCorner(24.dp)),
                ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
            ) {
               LaunchMainElements(rowItem = launchListRowModel, onInfoClick = {infoState=it}, infoState = infoState)

            }
        }
   // }
}

@Composable
fun LaunchMainElements(modifier: Modifier=Modifier,rowItem:LaunchListRowModel,onInfoClick:(Boolean)->Unit={},infoState:Boolean)
{
    var state by remember { mutableStateOf(false) }
    ConstraintLayout(modifier= modifier
        .fillMaxWidth()
        .padding(5.dp)){
        val topGuideline = createGuidelineFromTop(5.dp)
        val (icon, mission_name, nationality,year,view_more,secondary_elements) = createRefs()
        CardImage(size = 60.dp, icon = rowItem.pic?: ImageNotFound, lightSource = LightSource.RIGHT_TOP, modifier = Modifier.constrainAs(icon){
top.linkTo(parent.top)
            start.linkTo(parent.start)
            //bottom.linkTo(parent.bottom)
        })

        Text(text = rowItem.name ?: "", modifier = Modifier
            .constrainAs(mission_name) {
                top.linkTo(parent.top)
                start.linkTo(icon.end)
            }
            .padding(start = 5.dp),
        style = MaterialTheme.typography.titleMedium)


        Text(text = rowItem.nationality ?: "", modifier = Modifier
            .constrainAs(nationality) {
                top.linkTo(mission_name.bottom)
                start.linkTo(icon.end)
            }
            .padding(start = 5.dp))

        Text(text = rowItem.year ?: "", modifier = Modifier
            .constrainAs(year) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
            .padding(end = 5.dp))

        AnimatedVisibility(visible = state,modifier = Modifier.constrainAs(secondary_elements)
        {
            start.linkTo(parent.start)
            top.linkTo(icon.bottom)
            end.linkTo(parent.end)
            bottom.linkTo(view_more.top)

        }) {
            SecoundaryLaunchElements(launchListRowModel = rowItem, onInfoClick ={} , infoState =false)
        }

        Text(text =if(state) "Show Less" else "Show More",modifier = Modifier
            .constrainAs(view_more) {
                bottom.linkTo(parent.bottom)
                top.linkTo(secondary_elements.bottom)
                end.linkTo(parent.end)

            }
            .padding(end = 5.dp)
            .clickable(onClick = {
                state = !state
                // onInfoClick(state)

            }))

    }
}

@Composable
fun SecoundaryLaunchElements(modifier: Modifier=Modifier,launchListRowModel: LaunchListRowModel,onInfoClick: (Boolean) -> Unit,infoState:Boolean) {
   // var state by remember { mutableStateOf(infoState) }
    Column(verticalArrangement = Arrangement.Center,modifier = modifier
        .padding(5.dp)
        .fillMaxWidth()
        .clickable {
            /* state = !state
            onInfoClick(state)*/
        })
{
    Text("Rocket:"+launchListRowModel.rocket)
    Spacer(modifier = Modifier.height(5.dp))
    Text("Manufactured By:"+launchListRowModel.manufacturer)
    Spacer(modifier = Modifier.height(5.dp))
    Text("Details:"+launchListRowModel.details)



}
}

@Preview
@Composable
fun PreviewElement() {
    SpaceXAppTheme {
        LaunchListItem(launchListRowModel = LaunchListRowModel(name="Indo-SpaceX1", nationality = "Indian", year = "2023", pic = ImageNotFound))
    }
}


