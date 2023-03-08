package com.plinqdevelopers.dartplay.models.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BugEngineeringDTO(
    val engineeringAcceptance: String,
    val engineeringStatus: BugStatus,
    val engineeringDeveloper: String
) : Parcelable
