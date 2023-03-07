package com.plinqdevelopers.dartplay.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDAO {

    @Query("SELECT * FROM bug_items_tbl ORDER BY bug_itemID DESC")
    fun fetchBugsOrderedByLatest(): Flow<List<BugDTO>>

    @Query("SELECT * FROM bug_items_tbl WHERE bug_item_classificationDTO = :itemClassification ORDER BY bug_itemID DESC")
    fun filterBugsByClassification(
        itemClassification: BugClassification
    ): Flow<List<BugDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBugItem(bugItem: BugDTO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBugListWithServerResponse(
        bugItemsList: List<BugDTO>
    )

    @Query("DELETE From bug_items_tbl WHERE bug_itemID = :itemID")
    suspend fun deleteBugWithID(
        itemID: Int
    )
}
