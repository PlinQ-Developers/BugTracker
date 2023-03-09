package com.plinqdevelopers.dartplay.models.mappers

import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.models.local.BugEngineeringDTO
import com.plinqdevelopers.dartplay.models.local.BugStatus
import com.plinqdevelopers.dartplay.models.network.NetworkBug
import com.plinqdevelopers.dartplay.models.network.NetworkBugEngineering
import com.plinqdevelopers.dartplay.models.utils.IMapper
import javax.inject.Inject

class BugDTOMapper
@Inject
constructor(
    private val engineeringDTOMapper: BugEngineeringDTOMapper
) : IMapper<BugDTO, NetworkBug> {
    override fun mapToDomain(network: NetworkBug): BugDTO {
        return BugDTO(
            bugTicketID = network.bugTicketID,
            bugIsAlreadySynced = network.bugSynced,
            bugSubmittedDate = network.bugSubmittedDate,
            bugDescription = network.bugDesc,
            bugTitle = network.bugTitle,
            bugID = network.bugItemID,
            bugAccountID = network.bugAccountCreated,
            bugAttachments = network.bugAttachments,
            bugClassification = mapToBugClassification(
                type = network.bugType
            ),
            bugEngineering = engineeringDTOMapper.mapToDomain(
                network = network.bugEngineeringInfo
            )
        )
    }

    fun mapToDomainList(networkDTOs: List<NetworkBug>): List<BugDTO> {
        return networkDTOs.map {
            mapToDomain(
                network = it
            )
        }
    }
}

class BugEngineeringDTOMapper
@Inject
constructor() : IMapper<BugEngineeringDTO, NetworkBugEngineering> {
    override fun mapToDomain(network: NetworkBugEngineering): BugEngineeringDTO {
        return BugEngineeringDTO(
            engineeringAcceptance = network.bugAcceptedDate,
            engineeringDeveloper = network.bugDeveloper,
            engineeringStatus = mapToBugStatus(
                status = network.bugProgress
            )
        )
    }
}

private fun mapToBugStatus(status: String): BugStatus {
    return when (status) {
        "PENDING" -> BugStatus.PENDING
        "COMPLETED" -> BugStatus.COMPLETED
        "IN_TESTING" -> BugStatus.IN_TESTING
        "UNDER_DEVELOPMENT" -> BugStatus.UNDER_DEVELOPMENT
        else -> BugStatus.PENDING
    }
}

private fun mapToBugClassification(type: String): BugClassification {
    return when (type) {
        "COSMETIC" -> BugClassification.COSMETIC
        "CRITICAL" -> BugClassification.CRITICAL
        "MINOR" -> BugClassification.MINOR
        "OTHER" -> BugClassification.OTHER
        else -> BugClassification.OTHER
    }
}
