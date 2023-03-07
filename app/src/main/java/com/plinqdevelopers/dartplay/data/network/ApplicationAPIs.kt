package com.plinqdevelopers.dartplay.data.network

import retrofit2.http.GET

interface ApplicationAPIs {

    @GET("v3")
    fun loadNetworkBugList()
}
