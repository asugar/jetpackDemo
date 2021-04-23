package com.yi.rxjava

import com.yi.rxjavademo.mIntObserver
import io.reactivex.Observable
import io.reactivex.functions.Predicate

/**
 * Error Handling Operatores
 * @author wanghuayi
 * @version
 * @since 2021/4/23
 */

/**
 * catch
 * 捕获error，并重新发送
 */
private fun testCatch() {
    Observable.create<Int> { it.onNext(1 / 0) }
        .runCatching {
            println("testCatch runCatching")
            1
        }
        .onSuccess {
            println("testCatch $it")
        }
        .onFailure {
            println("testCache failt ${it.message}")
        }
}

/**
 * retry
 * 重试次数
 */
private fun testRetry() {
    Observable.create<Int> { it.onNext(1 / 0) }
        .retry(2, object : Predicate<Throwable> {
            override fun test(t: Throwable): Boolean {
                println("testRetry ${t.message}")
                return true
            }
        })
        .subscribe(mIntObserver)
}


fun main() {
    testRetry()
//    testCatch()
}