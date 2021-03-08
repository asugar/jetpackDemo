package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.*
import java.lang.reflect.Type

/**
 * 自定义序列化过程
 */
class UserSerializer : JsonSerializer<User> {

    override fun serialize(
        src: User?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty("name", src?.name)
        jsonObject.addProperty("address", src?.address)
        jsonObject.addProperty("cellPhone", src?.cellPhone)
//        jsonObject.addProperty("country", src?.country)
        val jsonArray = JsonArray()
        src?.title?.forEach {
            jsonArray.add(it)
        }
        jsonObject.add("title", jsonArray)
        return jsonObject
    }
}