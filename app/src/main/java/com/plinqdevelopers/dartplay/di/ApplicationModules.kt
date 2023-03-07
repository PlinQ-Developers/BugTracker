package com.plinqdevelopers.dartplay.di

import android.app.Application
import androidx.room.Room
import com.plinqdevelopers.dartplay.data.network.ApplicationAPIs
import com.plinqdevelopers.dartplay.data.room.ApplicationDB
import com.plinqdevelopers.dartplay.utils.ApplicationConstants.Companion.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModules {

    @Provides
    @Singleton
    fun provideRetrofitModule(): Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNetworkingModule(retrofit: Retrofit): ApplicationAPIs = retrofit.create(ApplicationAPIs::class.java)

    @Provides
    @Singleton
    fun provideDatabaseModule(application: Application): ApplicationDB = Room.databaseBuilder(
        application,
        ApplicationDB::class.java,
        "bugTrackApp.db"
    ).build()
}
