package com.yi.jetpackDemo.retrofit.entity

/**
 * 性能埋点实体类
 * http://yapi.aixuexi.com/project/431/interface/api/114711
 */
data class PerformanceLog(
    var commonFields: CommonFieldsLog? = null,
    var eventList: List<EventListItem>? = null// 日志列表
)

/**
 * 通用字段
 */
data class CommonFieldsLog(
    var uid: String = "",// 用户ID
    var deviceId: String = "",// 附加身份标识
    var projectId: String = "",// 项目ID
    var model: String = "",// 设备型号
    var system: String = "",// 设备系统
    var systemVersion: String = "",// 设备系统版本
    var channel: String = "",// 渠道
    var environment: String = "",// APP环境
    var brand: String = "", // 设备生产厂商
    var appVersion: String = "", // 产品app版本
    var netType: String = "", // 网络类型
    var longitude: String = "", // 经度
    var latitude: String = "", // 纬度
    var screenWidth: String = "", // 屏幕宽度
    var screenHeight: String = "" // 屏幕长度
)

data class EventListItem(
    var eventTime: String = "",//事件发生时间
    var type: Int = 0,//类型
    var url: String = "",//URL
    var code: String = "",//CODE
    var value: String = ""//性能详情/JSONString类型
)