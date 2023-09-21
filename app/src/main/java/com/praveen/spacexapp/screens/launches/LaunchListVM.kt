package com.praveen.spacexapp.screens.launches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.praveen.spacexapp.network.resources.ErrorResponse
import com.praveen.spacexapp.network.resources.NetworkRepository
import com.praveen.spacexapp.network.resources.SuccessResponse
import com.praveen.spacexapp.screens.LaunchModelPojo
import com.praveen.spacexapp.screens.ShipPojo
import com.praveen.spacexapp.utilities.ImageNotFound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListVM @Inject constructor(private var repository: NetworkRepository): ViewModel(){
    var TAG="LaunchListVM"

    private val _progressLoading= MutableStateFlow(false)
    val progressLoading=_progressLoading.asStateFlow()

    private val _selected_nation = MutableStateFlow("All")
    val selected_nation=_selected_nation.asStateFlow()

    fun onNationChanged(nation:String){
        _selected_nation.value=nation
    }

    private val _searchText = MutableStateFlow("")
    val searchText=_searchText.asStateFlow()

    fun onSearchText(search:String){
        _searchText.value=search
    }


    val list_of_nations= mutableSetOf<String>()
    private val _mutablelistOf_allLaunches=MutableStateFlow<List<LaunchListRowModel>>(listOf())
    val listOf_AllLaunches=_mutablelistOf_allLaunches.asStateFlow()

    fun getAllLaunches(){
        viewModelScope.launch {
            _progressLoading.value=true
            val launchResponse=repository.getAllLaunches()
            if(launchResponse is SuccessResponse){
                _mutablelistOf_allLaunches.value= launchResponse.data.map {

                    LaunchListRowModel(
                        name = it.mission_name,
                        id = it.flight_number,
                        details=it.details,
                        rocket=it.rocket?.rocket_name?:"",
                        year=it.launch_year?:"",
                        pic = it.links?.mission_patch?: ImageNotFound,
                        manufacturer = it.rocket?.second_stage?.payloads?.get(0)?.manufacturer?:"N/A",
                        nationality = it.rocket?.second_stage?.payloads?.get(0)?.nationality?:"N/A",
                        payload_type = it.rocket?.second_stage?.payloads?.get(0)?.payload_type?:"N/A"
                    )
                }.also {
                    it.forEach {item->
                        if(item.nationality!="N/A")
                            list_of_nations.add(item.nationality!!)
                    }
                    list_of_nations.add("All")

                }
            }





            if(launchResponse is ErrorResponse){
                Log.e(TAG,"Received Error "+launchResponse.message)
            }

            _progressLoading.value=false
        }

    }


    val main_launches=_selected_nation.combine(listOf_AllLaunches)
    { text,launches->
        when(text) {
            "All" -> {
                launches
            }
            else ->{
                launches.filter { it.nationality == text }
            }
        }
    }.combine(searchText){ launchs,text->
        if(text.isNullOrEmpty())
        launchs
        else
        {
            launchs.filter {
                (it.name?:"").contains(text,true) ||
                (it.year?:"").contains(text,true)
            }
        }
    }.stateIn(scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
    initialValue = _mutablelistOf_allLaunches.value)


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}