package com.plinqdevelopers.dartplay.data.network

import com.plinqdevelopers.dartplay.models.network.NetworkBug
import retrofit2.http.GET

interface ApplicationAPIs {
    @GET("b215398c-d15a-49ed-99f1-876af7deb740")
    suspend fun loadNetworkBugList(): List<NetworkBug>
}
