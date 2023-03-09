package com.plinqdevelopers.dartplay.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plinqdevelopers.dartplay.models.local.BugDTO
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [
        BugDTO::class
    ],
    version = 1
)
@TypeConverters(DataConverters::class)
abstract class ApplicationDB : RoomDatabase() {
    abstract fun databaseDAO(): DatabaseDAO
}
