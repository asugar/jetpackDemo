package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * 自定义反序列化
 */
class UserDeserializer : JsonDeserializer<User> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): User {
        val jsonObject = json?.asJsonObject
        val name = jsonObject?.get("name")?.asString
        val address = jsonObject?.get("address")?.asString
        val cellPhone = jsonObject?.get("cellPhone")?.asString
        val country = jsonObject?.get("country")?.asString
        val jsonArray = jsonObject?.getAsJsonArray("title")
        val title: Array<String> = Array(jsonArray?.size() ?: 0) { i -> "" }
        jsonArray?.forEachIndexed { index, jsonElement ->
            title[index] = jsonElement.asString
        }
        val user = User(0, name ?: "", address ?: "", cellPhone ?: "", country ?: "", title)
        return user
    }
}