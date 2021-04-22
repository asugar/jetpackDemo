package com.yi.rxjava

import com.yi.rxjavademo.mIntObserver
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.util.concurrent.Callable


/**
 * Transforming Observable
 * @author wanghuayi
 * @version
 * @since 2021/4/22
 */

/**
 * buffer
 * 周期性的打包items，一块发送
 * skip 每次打包新的起点
 * callable 泛型必须是Collection
 */
private fun testBuffer() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .buffer(3, 1, object : Callable<ArrayList<Int>> {
            override fun call(): ArrayList<Int> {
                return ArrayList()
            }
        })
        .subscribe {
            println("testBuffer $it")
        }
}

/**
 * flatMap
 * 把1个Observable -> 多个Observables -> 1个Observable
 * 返回的是Observable
 */
private fun testFlatMap() {
    Observable.create<Int> {
        it.onNext(1)
        it.onNext(2)
        it.onNext(3)
        it.onNext(4)
        it.onNext(5)
        it.onNext(6)
    }
//    Observable.just(1, 2, 3, 4, 5, 6)
        .flatMap<String>(object : Function<Int, Observable<String>> {
            override fun apply(t: Int): Observable<String> {
                return Observable.create { it.onNext(t.toString().plus("-a")) }
            }
        }).subscribe(com.yi.rxjavademo.mStringObserver)
}

/**
 * map
 * 1 -> 1 依次转化
 */
private fun testMap() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .map { it.toString().plus("-map") }
        .subscribe(com.yi.rxjavademo.mStringObserver)
}

/**
 * groupBy
 * 1 -> 多个组，每个组都有一个key
 */
private fun testGroupBy() {
    Observable.just(1, 2, 3, 4, 5)
        .groupBy(object : Function<Int, String> {
            override fun apply(t: Int): String {
//                return t.toString().plus("-groupBy")
                return if (t % 2 == 0) "true" else "false"
            }
        })
        .subscribe {
            println("testGroupBy ${it.key} ${it.toString()}")
        }
}

/**
 * scan
 * 应用一个函数返回函数结果作为下一次一个值，适合 xx数列
 */
private fun testScan() {
    Observable.just(1, 2, 3, 4, 5)
        .scan(object : BiFunction<Int, Int, Int> {
            override fun apply(t1: Int, t2: Int): Int {
                return t1 + t2
            }
        }).subscribe(mIntObserver)
}

/**
 * window
 * 分裂item -> Observable windows
 * bufferSize 不太理解
 * 和buffer类似
 */
private fun testWindow() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .window(3, 2, 1)
        .subscribe {
            println("testWindow $it")
            it.subscribe(mIntObserver)
        }
}


fun main() {
    testWindow()
//    testScan()
//    testGroupBy()
//    testMap()
//    testFlatMap()
//    testBuffer()
}