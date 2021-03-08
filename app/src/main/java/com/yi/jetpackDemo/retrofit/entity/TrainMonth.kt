package com.yi.jetpackDemo.retrofit.entity

data class TrainMonth(
    var yearMonth: String = "",
    var hasCourse: Int = 1, //是否有课程 0无 1有
    var month: Int = 0
) {
}