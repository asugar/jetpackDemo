package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class UserTypeAdapter : TypeAdapter<User>() {

    override fun write(out: JsonWriter?, value: User?) {
        out?.beginObject()
        out?.name("name")?.value(value?.name ?: "")
        out?.name("address")?.value(value?.address ?: "")
        out?.name("cellPhone")?.value(value?.cellPhone ?: "")
        out?.name("country")?.value(value?.country ?: "")
        out?.name("title")?.beginArray()
        value?.title?.forEachIndexed { index, s ->
            out?.value(s)
        }
        out?.endArray()
        out?.endObject()
    }

    override fun read(`in`: JsonReader?): User {
        val user = User()
        `in`?.beginObject()
        while (`in`?.hasNext() == true) {
            when (`in`.nextName()) {
                "name" -> {
                    user.name = `in`.nextString()
                }
                "address" -> {
                    user.address = `in`.nextString()
                }
                "cellPhone" -> {
                    user.cellPhone = `in`.nextString()
                }
                "country" -> {
                    user.country = `in`.nextString()
                }
                "title" -> {
                    `in`.beginArray()
                    val titles = ArrayList<String>()

                    while (`in`.hasNext()) {
                        titles.add(`in`.nextString())
                    }
                    val titleArray = Array<String>(titles.size) { "" }
                    titles.forEachIndexed { index, s ->
                        titleArray[index] = s
                    }
                    user.title = titleArray
                    `in`.endArray()
                }
            }
        }
        `in`?.endObject()
        return user
    }

}