package com.yi.jetpackDemo.retrofit.entiry

data class TrainMonth(
    var yearMonth: String = "",
    var hasCourse: Int = 1, //是否有课程 0无 1有
    var month: Int = 0
) {
}