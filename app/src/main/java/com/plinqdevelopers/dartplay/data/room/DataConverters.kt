package com.plinqdevelopers.dartplay.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.plinqdevelopers.dartplay.models.local.BugClassification
import com.plinqdevelopers.dartplay.models.local.BugEngineeringDTO
import com.plinqdevelopers.dartplay.models.local.BugStatus

class DataConverters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(
            list,
            object : TypeToken<List<String>>() {}.type
        )
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        return gson.fromJson(
            string,
            object : TypeToken<List<String>>() {}.type
        )
    }

    @TypeConverter
    fun fromClassENUM(classification: BugClassification?): String? {
        return gson.toJson(
            classification,
            object : TypeToken<BugClassification>() {}.type
        )
    }

    @TypeConverter
    fun toClassENUM(string: String?): BugClassification? {
        return gson.fromJson(
            string,
            object : TypeToken<BugClassification>() {}.type
        )
    }

    @TypeConverter
    fun fromStatusENUM(status: BugStatus?): String? {
        return gson.toJson(
            status,
            object : TypeToken<BugStatus>() {}.type
        )
    }

    @TypeConverter
    fun toStatusENUM(string: String?): BugStatus? {
        return gson.fromJson(
            string,
            object : TypeToken<BugStatus>() {}.type
        )
    }

    @TypeConverter
    fun fromEngineeringDTO(dto: BugEngineeringDTO?): String? {
        return gson.toJson(
            dto,
            object : TypeToken<BugEngineeringDTO>() {}.type
        )
    }

    @TypeConverter
    fun toEngineeringDTO(string: String?): BugEngineeringDTO? {
        return gson.fromJson(
            string,
            object : TypeToken<BugEngineeringDTO>() {}.type
        )
    }
}
