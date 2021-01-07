package com.yi.jetpackDemo.rxjava.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * 模块资源-升级接口 实体类
 * http://yapi.aixuexi.com/project/85/interface/api/112775
 */
data class ModuleResBean(
    var appId: Int = 0,//项目id
    var platformType: Int = 0,//平台 0-通用 1-Android 2-iOS 3-PC
    var modules: List<ModuleBean>? = null// 模块-如果没有资源，模块列表为空
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.createTypedArrayList(ModuleBean.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(appId)
        writeInt(platformType)
        writeTypedList(modules)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ModuleResBean> =
            object : Parcelable.Creator<ModuleResBean> {
                override fun createFromParcel(source: Parcel): ModuleResBean = ModuleResBean(source)
                override fun newArray(size: Int): Array<ModuleResBean?> = arrayOfNulls(size)
            }
    }
}