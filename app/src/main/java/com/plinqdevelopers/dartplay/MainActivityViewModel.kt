package com.plinqdevelopers.dartplay

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.plinqdevelopers.dartplay.data.network.Resources
import com.plinqdevelopers.dartplay.models.local.BugDTO
import com.plinqdevelopers.dartplay.repositories.BugItemsRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val itemsRepo: BugItemsRepoImpl
) : ViewModel(), LifecycleObserver {

    fun downloadApplicationData(): LiveData<Resources<List<BugDTO>>> {
        return itemsRepo.downloadDataAndSync().asLiveData()
    }
}
