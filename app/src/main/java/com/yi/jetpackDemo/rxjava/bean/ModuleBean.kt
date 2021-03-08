package com.yi.jetpackDemo.rxjava.bean

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils

/**
 * h5/rn资源包记录实体类
 * http://yapi.aixuexi.com/project/85/interface/api/112775
 */
data class ModuleBean(
    var packageTag: String = "",//业务模块标识
    var url: String = "",//资源下载地址
    var version: String = "",//版本号
    var description: String = "",//描述信息
    var updateType: Int = 0,//升级类型 0 非强制升，1强制升级，2静默升级
    var resourceType: Int = 1,//资源类型 1-Android 2-iOS 3-H5 4-PC 5-Android_RN 6-iOS_RN 9-PC补丁
    var resourceStatus: Int = 0//资源状态 1灰度，2上线
) : Parcelable {

    fun getBusinessTag(): String? {
        when (resourceType) {
            3 -> {
                return "prod_$packageTag"
            }
            5 -> {
                return "react_$packageTag"
            }
            else -> {
                return null
            }
        }
    }

    fun getResourceVersion(): Int {
        if (TextUtils.isEmpty(version)) {
            return 0
        }
        val tempArray = version.split("\\.".toRegex()).toTypedArray()
        var v = 0
        try {
            if (tempArray.size > 2) {
                v += tempArray[2].toInt()
            }
            if (tempArray.size > 1) {
                v += tempArray[1].toInt() * 1000
            }
            if (tempArray.isNotEmpty()) {
                v += tempArray[0].toInt() * 1000000
            }
        } catch (e: Exception) {
        }
        return v
    }

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readInt(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(packageTag)
        writeString(url)
        writeString(version)
        writeString(description)
        writeInt(updateType)
        writeInt(resourceType)
        writeInt(resourceStatus)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModuleBean

        if (packageTag != other.packageTag) return false

        return true
    }

    override fun hashCode(): Int {
        var result = packageTag.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + version.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + updateType
        result = 31 * result + resourceType
        result = 31 * result + resourceStatus
        return result
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModuleBean> = object : Parcelable.Creator<ModuleBean> {
            override fun createFromParcel(source: Parcel): ModuleBean = ModuleBean(source)
            override fun newArray(size: Int): Array<ModuleBean?> = arrayOfNulls(size)
        }
    }


}