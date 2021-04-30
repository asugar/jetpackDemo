package com.yi.jetpackDemo.service

/**
 * 服务类型枚举
 * @author wanghuayi
 * @version
 * @since 2021/4/29
 */
enum class ServiceType(val type: Int) {
    BINDER(1), MESSENGER(2), AIDL(3)
}