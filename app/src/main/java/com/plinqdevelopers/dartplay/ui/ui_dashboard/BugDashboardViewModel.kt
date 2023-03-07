package com.plinqdevelopers.dartplay.ui.ui_dashboard

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.repositories.BugItemsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BugDashboardViewModel
@Inject
constructor(
    private val itemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    val recentBugItems = itemsRepo.loadCachedBugsList().asLiveData()
    fun bugsByClassification(
        classification: BugClassification
    ): LiveData<List<BugDTO>> {
        return itemsRepo.filterBugsListByClassification(classification = classification).asLiveData()
    }
}
