package com.praveen.spacexapp.network.resources

import com.praveen.spacexapp.R
import com.praveen.spacexapp.di.MyApp
import com.praveen.spacexapp.network.RetrofitServices
import com.praveen.spacexapp.network.models.ShipModel
import com.praveen.spacexapp.network.models.launches.LaunchModel
import javax.inject.Inject

class NetworkRepository @Inject constructor(var retrofitServices:RetrofitServices) {
    private val errorMessage: String = "Something went wrong."

    suspend fun getAllShips(): Response<List<ShipModel>> = try {
        val isOnline = MyApp.getInstance().isOnline()
        if(isOnline) {
            var response=retrofitServices.getAllShips()
            SuccessResponse(response)
        } else {
            val internetException =
                NoInternetConnection(MyApp.getInstance().getString(
                    R.string.no_internet_connection))
            ErrorResponse(internetException.message ?:errorMessage, internetException)
        }
    } catch(e:Exception) {
        e.printStackTrace()
        ErrorResponse(e.message ?:errorMessage, e)
    }


    suspend fun getAllLaunches(): Response<List<LaunchModel>> = try {
        val isOnline = MyApp.getInstance().isOnline()
        if(isOnline) {
            var response=retrofitServices.getAllLaunches()
            if(response.isSuccessful)
            SuccessResponse(response.body()!!)
            else{
               handleHttpError(response)
            }
        } else {
            val internetException =
                NoInternetConnection(MyApp.getInstance().getString(
                    R.string.no_internet_connection))
            ErrorResponse(internetException.message ?:errorMessage, internetException)
        }
    } catch(e:Exception) {
        e.printStackTrace()
        ErrorResponse(e.message ?:errorMessage, e)
    }
}

