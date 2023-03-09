package com.plinqdevelopers.dartplay.ui.ui_create_report

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.repositories.BugItemsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateBugViewModel
@Inject
constructor(
    private val bugItemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    suspend fun saveUploadedBug(bugItem: BugDTO) {
        bugItemsRepo.saveBugItem(
            bugItem = bugItem
        )
    }
}
