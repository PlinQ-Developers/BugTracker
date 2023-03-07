package com.plinqdevelopers.dartplay.ui.ui_reports // ktlint-disable package-name

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
class BugReportsViewModel
@Inject
constructor(
    private val bugItemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    val bugItemsList = bugItemsRepo.loadCachedBugsList().asLiveData()

    fun filterListByClassification(
        classification: BugClassification
    ): LiveData<List<BugDTO>> {
        return bugItemsRepo.filterBugsListByClassification(classification = classification).asLiveData()
    }
}
