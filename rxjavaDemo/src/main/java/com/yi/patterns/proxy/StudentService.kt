package com.yi.patterns.proxy

class StudentService : IStudentService {

    override fun insertStudent() {
        println("StudentService insertStudent")
    }

    override fun deleteStudent() {
        println("StudentService deleteStudent")
    }
}