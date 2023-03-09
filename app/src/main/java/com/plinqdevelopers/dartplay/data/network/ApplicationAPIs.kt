package com.plinqdevelopers.dartplay.data.network

import com.plinqdevelopers.dartplay.models.network.NetworkBug
import retrofit2.http.GET

interface ApplicationAPIs {
    @GET("729c453c-3b0c-4ebf-9b6f-f725d933fae6")
    suspend fun loadNetworkBugList(): List<NetworkBug>
}
