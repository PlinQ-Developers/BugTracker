package com.plinqdevelopers.dartplay.ui.ui_create_report

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.repositories.BugItemsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateBugViewModel
@Inject
constructor(
    private val bugItemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    fun saveNewBugItem(bugItem: BugDTO) {
        createNewBugItem(bugItem = bugItem)
    }

    private fun createNewBugItem(bugItem: BugDTO) = viewModelScope.launch {
        bugItemsRepo.saveBugItem(
            bugItem = bugItem
        )
    }

}
