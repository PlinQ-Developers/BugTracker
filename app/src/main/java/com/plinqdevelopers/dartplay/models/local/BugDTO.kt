package com.plinqdevelopers.dartplay.models.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "bug_items_tbl"
)
@Parcelize
data class BugDTO(

    @PrimaryKey
    @ColumnInfo(name = "bug_itemID")
    val bugID: String,

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
) : Parcelable
