package com.praveen.spacexapp.network.models.launches

data class Rocket(
    var fairings: Fairings?,
    var first_stage: FirstStage?,
    var rocket_id: String?,
    var rocket_name: String?,
    var rocket_type: String?,
   var second_stage: SecondStage?
)