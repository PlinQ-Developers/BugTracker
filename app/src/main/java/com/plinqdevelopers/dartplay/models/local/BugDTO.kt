package com.plinqdevelopers.dartplay.models.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bug_items_tbl"
)
data class BugDTO(

    @PrimaryKey(
        autoGenerate = true
    )
    @ColumnInfo(name = "bug_itemID")
    val bugID: Int = 0,

    @ColumnInfo(name = "bug_item_title")
    val bugTitle: String,

    @ColumnInfo(name = "bug_item_ticketID")
    val bugTicketID: String,

    @ColumnInfo(name = "bug_item_accountID")
    val bugAccountID: String,

    @ColumnInfo(name = "bug_item_description")
    val bugDescription: String,

    @ColumnInfo(name = "bug_item_dateSubmitted")
    val bugSubmittedDate: String,

    @ColumnInfo(name = "bug_item_attachments")
    val bugAttachments: List<String>,

    @ColumnInfo(name = "bug_item_syncedValue")
    val bugIsAlreadySynced: Boolean = false,

    @Embedded
    val bugEngineering: BugEngineeringDTO,

    @ColumnInfo(name = "bug_item_classificationDTO")
    val bugClassification: BugClassification
)
