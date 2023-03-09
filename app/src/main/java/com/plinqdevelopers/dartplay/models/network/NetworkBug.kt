package com.plinqdevelopers.dartplay.models.network

import com.google.gson.annotations.SerializedName

data class NetworkBug(

    @SerializedName(value = "bug_item_id")
    val bugItemID: Long,

    @SerializedName(value = "bug_title")
    val bugTitle: String,

    @SerializedName(value = "bug_type")
    val bugType: String,

    @SerializedName(value = "bug_desc")
    val bugDesc: String,

    @SerializedName(value = "bug_ticket_id")
    val bugTicketID: String,

    @SerializedName(value = "bug_submitted_date")
    val bugSubmittedDate: String,

    @SerializedName(value = "bug_attachments")
    val bugAttachments: List<String>,

    @SerializedName(value = "bug_synced")
    val bugSynced: Boolean,

    @SerializedName(value = "bug_engineering_info")
    val bugEngineeringInfo: NetworkBugEngineering,

    @SerializedName(value = "bug_account_created")
    val bugAccountCreated: String
)

data class NetworkBugEngineering(
    @SerializedName(value = "bug_accepted_date")
    val bugAcceptedDate: String,

    @SerializedName(value = "bug_progress")
    val bugProgress: String,

    @SerializedName(value = "bug_developer")
    val bugDeveloper: String
)

