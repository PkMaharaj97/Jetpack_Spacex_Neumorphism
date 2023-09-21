package com.praveen.spacexapp.screens.launches

import com.praveen.spacexapp.utilities.ImageNotFound

data class LaunchListRowModel(
val name:String?="",
val id:Int?=0,
val pic:String?= ImageNotFound,
val year:String="",
val payload_type:String?="",
val nationality:String?="",
val manufacturer:String?="",
val details:String?="",
val rocket:String?=""
)

