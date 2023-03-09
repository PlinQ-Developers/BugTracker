package com.plinqdevelopers.dartplay.repositories

import com.plinqdevelopers.dartplay.data.network.ApplicationAPIs
import com.plinqdevelopers.dartplay.data.network.Resources
import com.plinqdevelopers.dartplay.data.network.networkBoundResource
import com.plinqdevelopers.dartplay.data.room.ApplicationDB
import com.plinqdevelopers.dartplay.data.room.DatabaseDAO
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.models.mappers.BugDTOMapper
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class BugItemsRepoImpl
@Inject
constructor(
    private val applicationDB: ApplicationDB,
    private val applicationAPIs: ApplicationAPIs,
    private val dtoMapper: BugDTOMapper
) {

    private val databaseDAO: DatabaseDAO = applicationDB.databaseDAO()

    fun downloadDataAndSync() = networkBoundResource(
        query = {
            databaseDAO.fetchBugsOrderedByLatest()
        },
        fetch = {
            applicationAPIs.loadNetworkBugList()
        },
        saveFetchedResults = {
            Timber.tag("t-logs").d("data: $it")
            databaseDAO.updateBugListWithServerResponse(
                bugItemsList = dtoMapper.mapToDomainList(
                    networkDTOs = it
                )
            )
        }
    )

    suspend fun deleteBugItemByID(itemID: Int) = databaseDAO.deleteBugWithID(
        itemID = itemID
    )

    fun filterBugsListByClassification(classification: BugClassification): Flow<List<BugDTO>> {
        return databaseDAO.filterBugsByClassification(
            itemClassification = classification
        )
    }

    fun loadCachedBugsList(): Flow<List<BugDTO>> {
        return databaseDAO.fetchBugsOrderedByLatest()
    }

    suspend fun saveBugItem(bugItem: BugDTO) = databaseDAO.saveBugItem(
        bugItem = bugItem
    )

    suspend fun saveBugItems(bugItems: List<BugDTO>) = databaseDAO.updateBugListWithServerResponse(
        bugItemsList = bugItems
    )
}
