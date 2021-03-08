package com.yi.patterns.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class StudentInvocationHandler : InvocationHandler {

    private var studentService: IStudentService

    constructor(studentService: IStudentService) {
        this.studentService = studentService
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        method?.invoke(studentService, *(args ?: arrayOfNulls<Any>(0)))
        return ""
    }
}