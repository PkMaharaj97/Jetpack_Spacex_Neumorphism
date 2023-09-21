package com.praveen.spacexapp.network

import com.praveen.spacexapp.network.models.ShipModel
import com.praveen.spacexapp.network.models.launches.LaunchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitServices {
    @GET("ships")
    suspend fun getAllShips():List<ShipModel>

    @GET("launches")
    suspend fun getAllLaunches():Response<List<LaunchModel>>
}

const val BASE_URL="https://api.spacexdata.com/v3/"