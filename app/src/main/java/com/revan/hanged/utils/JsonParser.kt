package com.revan.hanged.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonParser {
    val gson = Gson()

    inline fun <reified T> parseToJson(data: T): String {
        return gson.toJson(data)
    }

    inline fun <reified T> parseFromJson(json: String): T? {
        return try {
            val type = object : TypeToken<T>() {}.type
            gson.fromJson<T>(json, type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}