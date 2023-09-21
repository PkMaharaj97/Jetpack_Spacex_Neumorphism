package com.praveen.spacexapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.praveen.spacexapp.screens.HomeScreen
import com.praveen.spacexapp.screens.HomeVM
import com.praveen.spacexapp.screens.launches.LaunchListScreen
import com.praveen.spacexapp.screens.launches.LaunchListVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceXScreenNavigation() {
    val navController = rememberNavController()
    val launchListVM = hiltViewModel<LaunchListVM>()
    val homeVM = hiltViewModel<HomeVM>()

NavHost(navController = navController, startDestination = Screens.Home.route){
    composable(Screens.Home.route){
        HomeScreen(navController = navController,homeVM)
    }
    composable(Screens.LaunchList.route){
        LaunchListScreen(navController = navController,launchListVM)
    }

}
}