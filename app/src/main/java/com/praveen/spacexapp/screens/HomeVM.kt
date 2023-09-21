package com.praveen.spacexapp.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveen.spacexapp.network.models.launches.LaunchModel
import com.praveen.spacexapp.network.resources.ErrorResponse
import com.praveen.spacexapp.network.resources.NetworkRepository
import com.praveen.spacexapp.network.resources.SuccessResponse
import com.praveen.spacexapp.utilities.ImageNotFound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private var repository: NetworkRepository):ViewModel()
{
    var TAG="HomeVM"

   private val _progressLoading= MutableStateFlow(false)
    val progressLoading=_progressLoading.asStateFlow()

    private val _mutablelistOf_allShips=MutableStateFlow<List<ShipPojo>>(listOf())
    val listOf_AllShips=_mutablelistOf_allShips.asStateFlow()

    private val _mutablelistOf_allLaunches=MutableStateFlow<List<LaunchModelPojo>>(listOf())
    val listOf_AllLaunches=_mutablelistOf_allLaunches.asStateFlow()

    fun getAllShips(){
        viewModelScope.launch {
            _progressLoading.value=true
            var response=repository.getAllShips()
            if(response is SuccessResponse){
                _mutablelistOf_allShips.value=response.data.map {
                    ShipPojo(
                        ship_id = it.ship_id,
                        shipName = it.ship_name,
                        ship_image = it.image?: ImageNotFound
                    )
                }
            }
            if(response is ErrorResponse){
                Log.e(TAG,"Received Error"+response.message)
            }
            var launchResponse=repository.getAllLaunches()
            if(launchResponse is SuccessResponse){
             _mutablelistOf_allLaunches.value= launchResponse.data.map {
                    LaunchModelPojo(
                        name = it.mission_name,
                        id = it.flight_number,
                        pic = it.links?.mission_patch?: ImageNotFound
                    )
                }.subList(0,9)
            }
            if(launchResponse is ErrorResponse){
                Log.e(TAG,"Received Error "+launchResponse.message)
            }

            _progressLoading.value=false
        }

    }
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}