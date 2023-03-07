package com.plinqdevelopers.dartplay

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.repositories.BugItemsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val itemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    suspend fun uploadBugsList(bugsList: List<BugDTO>) {
        itemsRepo.saveBugItems(bugItems = bugsList)
    }
}
