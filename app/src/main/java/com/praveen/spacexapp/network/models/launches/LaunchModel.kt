package com.praveen.spacexapp.network.models.launches

data class LaunchModel(
    var flight_number: Int?,
    var is_tentative: Boolean?,
    var launch_date_local: String?,
    var launch_date_unix: Int?,
    var launch_date_utc: String?,
    var launch_success: Boolean?,
    var launch_window: Int?,
    var launch_year: String?,
    var links: Links?,
    var mission_id: List<String?>?,
    var mission_name: String?,
    var rocket: Rocket?,
    var tbd: Boolean?,
    var tentative_max_precision: String?,
    var details: String?
)