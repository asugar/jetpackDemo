package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Since
import com.google.gson.annotations.Until

/**
 * Gson链接
 * https://www.jianshu.com/p/fc5c9cdf3aab
 * Serialization 序列化：对象 to json字符串
 * Deserialization 反序列化：字符串 to 对象
 */
data class User(
    @Transient
    var id: Int = 0,
    @Expose(serialize = true, deserialize = true)// 支持序列化，可以分开指定序列化还是反序列化
    var name: String = "",
    @SerializedName("address") // 指定json中对应得字段名称；对象和json串中的字段名称可以不一样
    var address: String = "",
    @Since(2.0)// 从2.0版本生效，在GsonBuilder中指定version  无效
    var cellPhone: String = "",
    @Until(5.0)// 5.0版本之前都生效  无效
    var country: String = "",
    var title: Array<String>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (address != other.address) return false
        if (name != other.name) return false
        if (cellPhone != other.cellPhone) return false
        if (country != other.country) return false
        if (title != null) {
            if (other.title == null) return false
            if (title!!.size != other.title?.size) return false
        } else if (other.title != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + cellPhone.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + (title?.contentHashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', address='$address', cellPhone='$cellPhone', country='$country', title=${title?.contentToString()})"
    }

}