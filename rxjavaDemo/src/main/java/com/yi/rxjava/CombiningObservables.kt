package com.yi.rxjava

import io.reactivex.*
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * Combining Observables
 * @author wanghuayi
 * @version
 * @since 2021/4/19
 */

/**
 * startWith
 * 在开始的地方添加一个itme
 */
private fun testStartWith() {
    val just = Observable.just("Android", "IOS")
    val subscribe = just.startWith("Go").subscribe { println("$it") }
}

/**
 * merge
 * 合并多个Observables成一个序列
 * 合并多个观察者为一个
 */
private fun testMerge() {
    Observable.just(1, 2, 3)
        .mergeWith(Observable.just(1))
        .mergeWith(object : SingleSource<Int> {
            override fun subscribe(observer: SingleObserver<in Int>) {
                observer.onSuccess(123)
            }
        })
        .mergeWith(object : Maybe<Int>() {
            override fun subscribeActual(observer: MaybeObserver<in Int>?) {
                observer?.onSuccess(6)
                observer?.onError(Throwable("my fail"))
            }
        })
        .subscribe {
            println("merge result= $it")
        }
}

/**
 * mergeDelayError
 * 当所有的被观察者都发送之后，再单独发送error
 */
private fun testMergeDelayError() {
    val observable1 = Observable.error<String>(IllegalArgumentException("self error"))
    val observable2 = Observable.just("first", "second", "third")
    Observable.mergeDelayError(observable1, observable2)
        .subscribe {
            println("mergeDelay result= $it")
        }
}

/**
 * zip
 * 把多个被观察者合并成一个
 * zip vs merge
 * merge 是合并成一个序列发射
 * zip是按照对应进行自定义处理，再发射出去
 * 是一对一的，如果有不对应的就会舍去
 */
private fun testZip() {
    val firsts = Observable.just("hello", "world", "test")
    val seconds = Observable.just("Android", "IOS")
    firsts.zipWith(seconds, object : BiFunction<String, String, String> {
        override fun apply(t1: String, t2: String): String {
            return t1.plus("-").plus(t2)
        }
    }).subscribe({
        println("zip result= $it")
    }, {
        println("zip error ${it.message}")
    }, {
        println("zip onComplete")
    })
}

/**
 * combineLatest
 * 让最后一个组合，1A 2A 2B 3B 3C 4C
 */
private fun testCombineLatest() {
    val news = Observable.interval(100, TimeUnit.MILLISECONDS)
    val weathers = Observable.interval(50, TimeUnit.MILLISECONDS)
    Observable.combineLatest(news, weathers, object : BiFunction<Long, Long, String> {
        override fun apply(t1: Long, t2: Long): String {
            return "$t1 - $t2"

        }
    }).subscribe {
        println("testCombineLatest $it")
    }
}

/**
 * switchOnNext
 * 不太理解，从一边切换到另一边
 */
private fun testSwitchOnNext() {
    val timeIntervals = Observable.interval(1, TimeUnit.SECONDS)
        .map(object : Function<Long, Observable<String>> {
            override fun apply(t: Long): Observable<String> {
                return Observable.interval(100, TimeUnit.MILLISECONDS)
                    .map { innerInterval -> "outer: $t - inner: $innerInterval" }
            }
        })
    Observable.switchOnNext(timeIntervals)
        .subscribe({
            println("switchOnNext result= $it")
        }, {}, {})
}

/**
 * join
 * rxjava-join
 * and() then() when()
 * 有问题？？
 */
private fun testJoin() {
    Observable.create<String> {
        it.onNext("hello")
        it.onComplete()
    }.join(
        Observable.create<String> {
            it.onNext("World")
            it.onComplete()
        },
        object : Function<String, Observable<String>> {
            override fun apply(t: String): Observable<String> {
//                return Observable.just(t)
                return Observable.create {
                    println("function 1 $t $it")
                    it.onNext("left-")
                    it.onComplete()
                }
            }
        },
        object : Function<String, Observable<String>> {
            override fun apply(t: String): Observable<String> {
                return Observable.create {
                    println("function 2 $t $it")
                    it.onNext("right-")
                    it.onComplete()
                }
            }
        },
        object : BiFunction<String, String, String> {
            override fun apply(t1: String, t2: String): String {
                println("$t1 $t2")
                return "$t1 $t2"
            }
        }
    ).subscribe({
        println("testJoin $it")
    }, {}, {
        println("testJoin complete")
    })

}

fun main() {
    testJoin()
//    thread {
//        testSwitchOnNext()
//        testCombineLatest()
//        Thread.sleep(5000)
//    }
//    testZip()
//    testMergeDelayError()
//    testMerge()
//    testStartWith()
}

