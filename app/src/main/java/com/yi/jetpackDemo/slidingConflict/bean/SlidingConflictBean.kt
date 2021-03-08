package com.yi.jetpackDemo.slidingConflict.bean

import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.slidingConflict.SlidingConflictFragment

data class SlidingConflictBean(
    var title: String = "",
    var items: List<SlidingConflictItemBean>
)

data class SlidingConflictItemBean(
    var imgRes: Int = 0
)

fun SlidingConflictFragment.getTestDatas(): List<SlidingConflictBean> {
    val list = ArrayList<SlidingConflictBean>()
    for (i in (0..10)) {
        val items = ArrayList<SlidingConflictItemBean>()
        items.add(SlidingConflictItemBean(R.mipmap.image1))
        items.add(SlidingConflictItemBean(R.mipmap.image2))
        items.add(SlidingConflictItemBean(R.mipmap.image3))
        items.add(SlidingConflictItemBean(R.mipmap.image4))
        items.add(SlidingConflictItemBean(R.mipmap.image5))
        items.add(SlidingConflictItemBean(R.mipmap.image6))
        list.add(SlidingConflictBean("Titile-$i", items))
    }
    return list
}