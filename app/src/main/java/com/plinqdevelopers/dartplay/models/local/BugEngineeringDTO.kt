package com.plinqdevelopers.dartplay.models.local

data class BugEngineeringDTO(
    val engineeringAcceptance: String,
    val engineeringStatus: BugStatus,
    val engineeringDeveloper: String
)
