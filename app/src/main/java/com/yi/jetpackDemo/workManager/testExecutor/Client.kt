package com.yi.jetpackDemo.workManager.testExecutor

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

fun main() {
    /**
     * 核心线程数
     * 最大线程数
     * 核心线程空闲存活时间
     * 阻塞队列
     */
//    val ex = ThreadPoolExecutor(2, 6, 100, TimeUnit.MILLISECONDS, LinkedBlockingDeque<Runnable>())
    val ex1 = Executors.newFixedThreadPool(1)
    val t1 = MyTask();
    val t2 = MyTask();
    val t3 = MyTask();
    val t4 = MyTask();

    ex1.execute(t1)
    ex1.execute(t2)
    ex1.execute(t3)
    ex1.execute(t4)

    ex1.shutdown()

}

class MyTask : Runnable {
    override fun run() {
        println(Thread.currentThread().name + " 正在执行。。。")
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}