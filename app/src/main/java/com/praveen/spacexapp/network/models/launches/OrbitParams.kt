package com.praveen.spacexapp.network.models.launches

data class OrbitParams(
    var apoapsis_km: Double?,
    var arg_of_pericenter: Double?,
    var eccentricity: Double?,
    var epoch: String?,
    var inclination_deg: Double?,
    var lifespan_years: Int?,
    var longitude: Double?,
    var mean_anomaly: Double?,
    var mean_motion: Double?,
    var periapsis_km: Double?,
    var period_min: Double?,
    var raan: Double?,
    var reference_system: String?,
    var regime: String?,
    var semi_major_axis_km: Double?
)