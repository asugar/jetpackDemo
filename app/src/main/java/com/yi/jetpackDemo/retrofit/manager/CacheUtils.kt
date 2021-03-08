package com.yi.jetpackDemo.retrofit.manager

import android.net.Uri

const val CACHE_QUERY: String = "CACHEQUERY"
val PARAMETER_SEPARATOR = "&"
val NAME_VALUE_SEPARATOR = "="

// 将原请求中的CACHE_QUERY参数去除并返回新的Url
fun removeCacheQuery(source: String): String {
    val uri = Uri.parse(source)
    val query = uri.query
    val parameters =
        query?.split(PARAMETER_SEPARATOR)
    val newParameters = ArrayList<String>()
    parameters?.filterNotTo(newParameters) {
        it.startsWith(CACHE_QUERY)
    }
    val builder = Uri.Builder()
    builder.scheme(uri.scheme).path(uri.path).authority(uri.authority)
    newParameters
        .map { it.split(NAME_VALUE_SEPARATOR) }
        .forEach { builder.appendQueryParameter(it[0], it[1]) }
    return builder.build().toString()
}