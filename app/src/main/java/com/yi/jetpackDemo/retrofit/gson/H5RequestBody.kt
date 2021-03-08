package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/**
 * 测试
 */
fun main() {
    val jsonStr = "{\n" +
            "    \"method\": \"post\",\n" +
            "    \"param\":\n" +
            "    {\n" +
            "        \"dateText\": \"semester\",\n" +
            "        \"classTimeList\": [-1,-2,3,4,5],\n" +
            "        \"moudleType\": 4,\n" +
            "        \"timeType\": -1,\n" +
            "        \"userId\": \"2416522\",\n" +
            "        \"assistantId\": \"\",\n" +
            "        \"insId\": 603,\n" +
            "        \"teacherId\": \"52871\",\n" +
            "        \"dateType\": 3,\n" +
            "        \"timeText\": \"2021寒\",\n" +
            "        \"classTimeText\": \"全部时段\",\n" +
            "        \"beginTime\": \"2021-1-1\",\n" +
            "        \"endTime\": \"2021-2-28\",\n" +
            "        \"testObj\": {\n" +
            "        \t\"key1\":\"key1\",\n" +
            "        \t\"key2\":\"key2\",\n" +
            "        \t\"key3\":\"key3\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"url\": \"https://schoolmaster.aixuexi.com/godfather/app/reCorrect/getSjkIndexViewData\"\n" +
            "}"
    val body = getGson().fromJson<H5RequestBody>(jsonStr, H5RequestBody::class.java)
    println("${body.toString()}")
}

private fun getGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(H5RequestBody::class.java, H5ReqTypeAdapter())
    return gsonBuilder.create()
}

/**
 * 解析h5请求的body
 * h5传递过来的参数，param的value有可能时数组，也有可能时object，此是需要特殊处理
 * 数组使用,隔开
 * 对象直接用jsonStr
 */
data class H5RequestBody(
    var method: String = "",
    var url: String = "",
    var param: Map<String, String>? = null
) {
    override fun toString(): String {
        return "H5RequestBody(method='$method', url='$url', param=$param)"
    }
}

class H5ReqTypeAdapter : TypeAdapter<H5RequestBody>() {

    override fun write(jsonWriter: JsonWriter?, value: H5RequestBody?) {

    }

    override fun read(jsonReader: JsonReader?): H5RequestBody {
        val body = H5RequestBody()
        jsonReader?.beginObject()
        while (jsonReader?.hasNext() == true) {
            when (jsonReader.nextName()) {
                "url" -> {
                    body.url = jsonReader.nextString()
                }
                "method" -> {
                    body.method = jsonReader.nextString()
                }
                "param" -> {
                    val map = HashMap<String, String>()
                    jsonReader.beginObject()
                    while (jsonReader.hasNext()) {
                        val keyName = jsonReader.nextName()
                        val jsonToken = jsonReader.peek()
                        println("key= $keyName jsonToken= $jsonToken")
                        when (jsonToken) {
                            JsonToken.STRING -> {
                                val value = jsonReader.nextString()
                                map[keyName] = value
                                println("STRING $value")
                            }
                            JsonToken.BEGIN_ARRAY -> {
                                jsonReader.beginArray()
                                var sb = StringBuilder()
                                while (jsonReader.hasNext()) {
                                    sb.append(jsonReader.nextString()).append(",")
                                }
                                sb = sb.deleteCharAt(sb.lastIndex)
                                map[keyName] = sb.toString()
                                jsonReader.endArray()
                                println("BEGIN_ARRAY $sb")
                            }
                            JsonToken.NUMBER -> {
                                val temp = jsonReader.nextDouble()
                                val value = if (temp.compareTo(temp.toLong()) == 0) {
                                    if (temp.toLong() > Int.MAX_VALUE) {
                                        temp.toLong()
                                    } else {
                                        temp.toInt()
                                    }
                                } else {
                                    temp
                                }
                                map[keyName] = value.toString()
                                println("NUMBER $value")
                            }
                            JsonToken.BEGIN_OBJECT -> {
                                println("BEGIN_OBJECT ")
                                jsonReader.beginObject()
                                while (jsonReader.hasNext()){
                                    map[keyName] = jsonReader.skipValue().toString()
                                }
                                jsonReader.endObject()
                            }
                            else -> {
                                println("else ")
                            }
                        }
                    }
                    jsonReader.endObject()
                    body.param = map
                }
            }
        }
        jsonReader?.endObject()
        return body


    }


}