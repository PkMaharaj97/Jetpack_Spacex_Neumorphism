package com.praveen.spacexapp.navigation

sealed class Screens(val route:String) {
    object Home : Screens("home_screen")/*{
        const val locationArg = "locationName"
        val routeWithArgs = "$route/{$locationArg}"
    }*/
    object LaunchList : Screens("launch_list_screen")

}