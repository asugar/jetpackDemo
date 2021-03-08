package com.yi.patterns.proxy

class StudentServiceProxy : IStudentService {

    private var studentService: IStudentService

    constructor(studentService: IStudentService) {
        this.studentService = studentService
    }

    override fun insertStudent() {
        println("准备添加学生")
        studentService.insertStudent()
        println("添加学生成功")
    }

    override fun deleteStudent() {
        println("准备删除学生")
        studentService.deleteStudent()
        println("删除学生成功")
    }
}