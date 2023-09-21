package com.praveen.spacexapp.network.models.launches

data class Core(
    var block: Int?,
    var core_serial: String?,
    var flight: Int?,
    var gridfins: Boolean?,
    var land_success: Boolean?,
    var landing_intent: Boolean?,
    var landing_type: String?,
    var landing_vehicle: String?,
    var legs: Boolean?,
    var reused: Boolean?
)