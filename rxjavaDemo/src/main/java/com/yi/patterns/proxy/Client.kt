package com.yi.patterns.proxy

import java.lang.reflect.Proxy

fun main() {
    val studentService = StudentService()
    val studentServiceProxy = StudentServiceProxy(studentService)
//    studentServiceProxy.insertStudent()
//    studentServiceProxy.deleteStudent()

    val proxy = StudentInvocationHandler(studentService)
//    val deleteMethod = studentService.javaClass.getMethod("insertStudent")
//    proxy.invoke(studentService, deleteMethod, emptyArray())


    val studentServiceProxy2 = Proxy.newProxyInstance(
        proxy.javaClass.classLoader,
        studentService.javaClass.interfaces,
        proxy
    ) as IStudentService
    studentServiceProxy2.insertStudent()
    studentServiceProxy2.deleteStudent()
}