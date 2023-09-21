package com.praveen.spacexapp.network.models.launches

data class Payload(
    var customers: List<String?>?,
    var manufacturer: String?,
    var nationality: String?,
    var norad_id: List<Int?>?,
    var orbit: String?,
  //  var orbit_params: OrbitParams?,
    var payload_id: String?,
    var payload_mass_kg: Double?,
    var payload_mass_lbs: Double?,
    var payload_type: String?,
    var reused: Boolean?
)