package com.praveen.spacexapp.network.models.launches

data class Fairings(
    var recovered: Boolean?,
    var recovery_attempt: Boolean?,
    var reused: Boolean?,
    var ship: Any?
)